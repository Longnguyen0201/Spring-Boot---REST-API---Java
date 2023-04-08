package com.rest.api.utils.response;

import com.rest.api.model.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class PostResponseDTO {
    private Long id;

    @NotNull(message = "Title is require")
    @NotEmpty(message = "Field is not empty")
    private String title;

    @NotNull(message = "description is require")
    @NotEmpty(message = "Field is not empty")
    private String description;

    private Integer numberComments;

    @NotNull(message = "content is require")
    @NotEmpty(message = "Field is not empty")
    private String content;

    private Set<CommentResponseDTO> commentSet;
}
