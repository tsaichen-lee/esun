package com.esun.social.repository;

import com.esun.social.dto.CommentResponseDTO;
import com.esun.social.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    @Query("SELECT new com.esun.social.dto.CommentResponseDTO(c.id, c.content, c.postId, c.user.username) " +
            "FROM Comment c JOIN User u ON c.userId = u.id WHERE c.postId = :postId")
    List<CommentResponseDTO> findCommentsWithUsernameByPostId(@Param("postId") Long postId);

}
