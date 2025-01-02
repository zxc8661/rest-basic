package com.ll.rest.domain.post.post.service.repository;


import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    public Post write(String title, String content) {
        Post post = Post.builder().
                title(title).
                content(content).
                build();
        return this.postRepository.save(post);
        //insert into post(created_at,modified_at,content,title) values(?,?,?,?);
    }
}
