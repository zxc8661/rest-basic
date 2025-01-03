package com.ll.rest.domain.post.post.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ll.rest.domain.post.post.entity.Post;

import com.ll.rest.global.jpa.entity.BaseTime;
import lombok.*;

import java.time.LocalDateTime;


@Getter
public class PostDto {

    private Long id;
    @JsonProperty("createdDatetime")
    private LocalDateTime createDate;
    @JsonProperty("modifiedDatetime")
    private LocalDateTime modifyDate;
    private String title;
    private String content;


    public PostDto(Post post){
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifyDate();
        this.title = post.getTitle();
        this.content = post.getContent();
    }



}
