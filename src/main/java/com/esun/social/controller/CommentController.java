package com.esun.social.controller;

import com.esun.social.dto.CommentResponseDTO;
import com.esun.social.entity.Comment;
import com.esun.social.entity.User;
import com.esun.social.service.CommentService;
import com.esun.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    // 傳統留言（不含異動多表）
    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponseDTO> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsWithUsernames(postId);
    }

    // 使用 @Transactional 的留言操作（新增留言 + 更新留言數）
    @PostMapping("/tx")
    public Comment addCommentTransactional(@RequestBody Comment comment) {
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByPhone(phone);
        comment.setUserId(user.getId());
        return commentService.addCommentWithCount(comment);
    }

}
