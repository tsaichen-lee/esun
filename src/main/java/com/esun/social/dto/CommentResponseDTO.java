package com.esun.social.dto;

import lombok.Getter;

@Getter
public class CommentResponseDTO {
    private Long id;
    private String content;
    private Long postId;
    private String username;

    public CommentResponseDTO(Long id, String content, Long postId, String username) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.username = username;
    }

}
