package com.esun.social.service;

import com.esun.social.dto.CommentResponseDTO;
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

    public List<CommentResponseDTO> getCommentsWithUsernames(Long postId) {
        return commentRepository.findCommentsWithUsernameByPostId(postId);
    }

    // 加留言 + 更新貼文留言數
    @Transactional
    public Comment addCommentWithCount(Comment comment) {
        Comment saved = commentRepository.save(comment);
        postService.incrementCommentCount(comment.getPostId());
        return saved;
    }

}
