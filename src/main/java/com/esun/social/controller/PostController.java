package com.esun.social.controller;

import com.esun.social.entity.Post;
import com.esun.social.service.PostService;
import com.esun.social.service.UserService;
import com.esun.social.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @PostMapping
    public Post createPost(@RequestBody Post post) {
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByPhone(phone);
        post.setUserId(user.getId());
        return postService.createPost(post);
    }


    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        return postService.updatePost(id, updatedPost);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

}
