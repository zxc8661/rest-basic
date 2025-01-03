package com.ll.rest.domain.post.post.controller;

import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.domain.post.post.entity.dto.PostDto;
import com.ll.rest.domain.post.post.service.repository.PostService;
import com.ll.rest.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;
    @GetMapping
    public List<PostDto> getItems(){
        return postService.findAllByOrderByIdDesc()
                .stream()
                .map(PostDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public PostDto getItem(@PathVariable("id") long id){
       return this.postService.findById(id)
                    .map(PostDto::new)
                    .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public RsData<Void>  deleteItem(@PathVariable("id") Long id){
         Post post = this.postService.findById(id).get();

         this.postService.delete(post);



        return new RsData<>(
                "200-1",
                "%d번 글이 삭제되었습니다.".formatted(id)
        );


    }
    record PostModifyReqBody(
            @NotBlank
            @Length(min = 2)
            String title,
            @NotBlank
            @Length(min = 2)
            String content
    ) {
    }
    @PutMapping("/{id}")
    @Transactional
    public RsData<PostDto> modifyItem(@PathVariable("id") Long id,
                                         @RequestBody @Valid PostModifyReqBody reqBody){

        Post post = this.postService.findById(id).get();

        postService.modify(post, reqBody.title, reqBody.content);



        return new RsData<PostDto>("200-1"
                ,"%d번 글을 수정 하였습니다.".formatted(id)
                ,new PostDto(post));
    }


    record PostWriteResBody(
            PostDto item,
            long totalCount
    ) {
    }
    record PostWriteReqBody(
            @NotBlank
            @Length(min = 2)
            String title,
            @NotBlank
            @Length(min = 2)
            String content
    ) {
    }

    @PostMapping
    public RsData<PostWriteResBody> writeItem(
                             @RequestBody @Valid PostWriteReqBody reqBody){



        Post post =this.postService.create( reqBody.title, reqBody.content);


        return new RsData<>(
                "201-1",
                "%d번 글이 작성되었습니다.".formatted(post.getId()),
                new PostWriteResBody(
                        new PostDto(post),
                        postService.count()
                )
        );
    }



}

