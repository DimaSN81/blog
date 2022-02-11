package com.example.blog.service;

import com.example.blog.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> findAllPost();

    Post savePost(Post post);

    Post savePostById(Post post, long postId);

    void deletePostById(long id);

    List<Post> findByTitle(String title);

    List<Post> findAllPost(String sort);

}