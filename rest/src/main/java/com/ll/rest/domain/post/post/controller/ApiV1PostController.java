package com.ll.rest.domain.post.post.controller;

import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.domain.post.post.service.repository.PostService;
import com.ll.rest.global.rsData.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    public List<Post> getItems(){
        return postService.findAllByOrderByIdDesc();
    }

    @GetMapping("/{id}")
    public Post getItem(@PathVariable("id") Long id){
        return this.postService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public RsData deleteItem(@PathVariable("id") Long id){
         Post post = this.postService.findById(id).get();

         this.postService.delete(post);



         return new RsData("200-1","%d번 글을 삭제 하였습니다.".formatted(id));
    }
    record PostModifyReqBody(
            String title,
            String content
    ) {
    }
    @PutMapping("/{id}")
    @Transactional
    public RsData modifyItem(@PathVariable("id") Long id,
                                         @RequestBody PostModifyReqBody reqBody){

        Post post = this.postService.findById(id).get();

        postService.modify(post, reqBody.title, reqBody.content);



        return new RsData("200-1","%d번 글을 수정 하였습니다.".formatted(id));
    }

}

