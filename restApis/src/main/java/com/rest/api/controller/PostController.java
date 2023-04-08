package com.rest.api.controller;

import com.rest.api.service.PostService;
import com.rest.api.utils.ValidObject;
import com.rest.api.utils.ValidateUtils;
import com.rest.api.utils.request.PostDTO;
import com.rest.api.utils.response.PostResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping({"/all", ""})
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> creteComment( @RequestBody PostDTO dto) {
        Map<String,String> errorValidate = ValidObject.validatePostDTO(dto);
        if (!ObjectUtils.isEmpty(errorValidate)){
            return new ResponseEntity<>(errorValidate,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateComment(@RequestParam("idUpdate") Long id,
                                                @RequestBody PostDTO dto) {
        return new ResponseEntity<>(postService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idDelete}")
    public ResponseEntity<String> deleteComent(@PathVariable("idDelete") Long id){
        return new ResponseEntity<>(postService.delete(id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCommentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);
    }

}
