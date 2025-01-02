package com.ll.rest.domain.post.post.service.repository;


import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Post> findAllByOrderByIdDesc() {
        return postRepository.findAllByOrderByIdDesc();
    }

    public Optional<Post> findById(Long id) {
        return this.postRepository.findById(id);
    }

    public void delete(Post post) {
        this.postRepository.delete(post);
    }

    public void modify(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content);
    }

    public Post create(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        this.postRepository.save(post);
        return post;
    }
}
