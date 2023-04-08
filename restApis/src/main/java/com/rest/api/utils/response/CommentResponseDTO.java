package com.rest.api.utils.response;

import com.rest.api.model.Post;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CommentResponseDTO {
    private Long id;

    private String name;
    private String email;
    private String body;

    private Post post;
}
