package com.example.blog.service;

import com.example.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment getById(Long id);

    List<Comment> getByPostId(Long postId);

    Comment addByPostId(Comment comment, Long postId);

}
