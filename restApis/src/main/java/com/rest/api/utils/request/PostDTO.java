package com.rest.api.utils.request;

import com.rest.api.model.Comment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {


    private Long id;

    private String title;

    private String description;

    private Integer numberComments;

    private String content;

}
