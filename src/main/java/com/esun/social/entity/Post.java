package com.esun.social.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // 可搭配 security context 放入登入者ID

    private String content;

    private String image;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Integer commentCount = 0;
}
