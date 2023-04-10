package com.rest.api.controller;

import com.rest.api.model.Comment;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.CommentService;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping({"/all", ""})
    private ResponseEntity<List<CommentResponseDTO>> getAllComment() {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentDTO commentDto) {
        return new ResponseEntity<>(commentService.save(commentDto), HttpStatus.CREATED);
    }

}
