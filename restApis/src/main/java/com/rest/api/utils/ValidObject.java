package com.rest.api.utils;

import com.rest.api.utils.request.PostDTO;

import java.util.HashMap;
import java.util.Map;

public class ValidObject {

    public static Map<String,String> validatePostDTO(PostDTO postDTO){
        Map<String,String> errors = new HashMap<>();

       errors.putAll(ValidateUtils.builder()
                .fieldName("title")
                .value(postDTO.getTitle())
                .require(true)
                .maxLength(20)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("description")
                .value(postDTO.getDescription())
                .require(false)
                .maxLength(50)
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("numberComments")
                .value(postDTO.getNumberComments())
                .require(true)
                .isInteger(true)
                .min(Long.valueOf(0))
                .max(Long.valueOf(50))
                .build().validate());

        errors.putAll(ValidateUtils.builder()
                .fieldName("content")
                .value(postDTO.getContent())
                .require(true)
                .maxLength(30)
                .build().validate());

        return errors;
    }
}
