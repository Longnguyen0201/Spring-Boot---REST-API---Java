package com.rest.api.service.impl;

import com.rest.api.errors.ResourceNotFoundException;
import com.rest.api.model.Comment;
import com.rest.api.model.Post;
import com.rest.api.repository.CommentRepository;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.CommentService;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<CommentResponseDTO> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments
                .stream()
                .map(c-> mapperToCommentDto(c))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Comment not found"));
        return Optional.of(mapperToCommentDto(comment));
    }

    @Override
    public CommentResponseDTO save(CommentDTO commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(()-> new ResourceNotFoundException("Post not found with id: "+commentDto.getPostId()));

        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);
        return mapperToCommentDto(saveComment);
    }

    @Override
    public CommentResponseDTO update(CommentDTO commentDTO, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found to update with id: "+id));
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(()-> new ResourceNotFoundException("Post not found"));
        comment.setEmail(commentDTO.getEmail());
        comment.setName(commentDTO.getName());
        comment.setBody(commentDTO.getBody());
        comment.setPost(post);
        return mapperToCommentDto(commentRepository.save(comment));
    }

    @Override
    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Successfully";
    }

    public static CommentResponseDTO mapperToCommentDto(Comment comment){
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setPost(comment.getPost());
        return dto;
    }
}
