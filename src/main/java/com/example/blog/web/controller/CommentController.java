package com.example.blog.web.controller;

import com.example.blog.entity.Comment;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping(path = "/posts/{postId}/comments")
    public List<Comment> getAllCommentByPostId(@PathVariable Long postId) {
        return commentService.getByPostId(postId);
    }

    @GetMapping(path = "comments/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentService.getById(commentId);
    }

    @PostMapping(path = "/posts/{postId}/comments")
    public Comment addCommentByPostId(@RequestBody Comment comment, @PathVariable Long postId) {
        return commentService.addByPostId(comment, postId);
    }

}
