package com.rest.api.service;

import com.rest.api.model.Comment;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.utils.response.CommentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentResponseDTO> getAll();

    Optional<CommentResponseDTO> findById(Long id);

    CommentResponseDTO save(CommentDTO commentDto);

    CommentResponseDTO update(CommentDTO commentDTO, Long id);

    String delete(Long id);
}
