package com.ll.rest.domain.post.post.entity.dto;

import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;


@Getter
public class PostDto extends BaseTime {

    private Long id;
    private String title;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDatetime;
    private String content;



    public PostDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content=post.getContent();
        this.createdDate = post.getCreatedAt();
        this.modifiedDatetime = post.getModifiedAt();
    }
}
