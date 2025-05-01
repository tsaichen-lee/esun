package com.esun.social.service;

import com.esun.social.entity.Post;
import com.esun.social.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Long id, Post updatedPost) {
        Post existing = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        existing.setContent(updatedPost.getContent());
        existing.setImage(updatedPost.getImage());
        return postRepository.save(existing);
    }

    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }

    public void incrementCommentCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);
    }


}
