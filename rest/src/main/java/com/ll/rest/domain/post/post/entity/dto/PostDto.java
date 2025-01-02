package com.ll.rest.domain.post.post.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.rest.domain.post.post.entity.Post;

import com.ll.rest.global.jpa.entity.BaseTime;
import lombok.*;

import java.time.LocalDateTime;


@Getter
public class PostDto {

    private Long id;
    @JsonIgnore
    private LocalDateTime createDate;
    @JsonIgnore
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

    public LocalDateTime getCreatedDatetime() {
        return createDate;
    }
    public LocalDateTime getModifiedDatetime() {
        return modifyDate;
    }
}
