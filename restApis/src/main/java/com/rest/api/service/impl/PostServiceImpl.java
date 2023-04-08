package com.rest.api.service.impl;

import com.rest.api.errors.ResourceNotFoundException;
import com.rest.api.model.Post;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.PostService;
import com.rest.api.utils.request.PostDTO;
import com.rest.api.utils.response.PostResponseDTO;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rest.api.service.impl.CommentServiceImpl.mapperToCommentDto;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostResponseDTO> getAll() {
        return postRepository.findAll()
                .stream()
                .map(post -> mapperToPostDto(post))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponseDTO> findById(Long id) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id: " + id));

        return Optional.of(mapperToPostDto(post));
    }

    @Override
    public PostResponseDTO save(PostDTO dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());

        Post saved = postRepository.save(post);
        return mapperToPostDto(saved);
    }

    @Override
    public PostResponseDTO update(PostDTO dto, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Post not found with id: " + id));
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        postRepository.save(post);
        return mapperToPostDto(post);
    }

    @Override
    public String delete(Long id) {
        postRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Post not found with id: " + id));
        postRepository.deleteById(id);
        return "Delete successfully";
    }

    private PostResponseDTO mapperToPostDto(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
//        if(post.getCommentSet() != null && post.getCommentSet().size()>0){
        if (!ObjectUtils.isEmpty(post.getCommentSet())){
            dto.setCommentSet(post.getCommentSet()
                    .stream()
                    .map(CommentServiceImpl::mapperToCommentDto)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }
}
