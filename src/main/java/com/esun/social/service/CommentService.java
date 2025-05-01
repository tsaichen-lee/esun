package com.esun.social.service;

import com.esun.social.entity.Comment;
import com.esun.social.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    // 傳統新增留言
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // 根據貼文 ID 查留言
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // 加留言 + 更新貼文留言數
    @Transactional
    public Comment addCommentWithCount(Comment comment) {
        Comment saved = commentRepository.save(comment);
        postService.incrementCommentCount(comment.getPostId());
        return saved;
    }

    public Comment updateComment(Long id, String newContent) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(newContent);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment not found");
        }
        commentRepository.deleteById(id);
    }

}
