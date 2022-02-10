package com.example.blog.service;

import com.example.blog.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPost();

    Post savePost(Post post);

    Post savePostById(Post post, long postId);

    void deletePostById(long id);
}