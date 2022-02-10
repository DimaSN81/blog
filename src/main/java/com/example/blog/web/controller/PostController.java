package com.example.blog.web.controller;

import com.example.blog.entity.Post;
import com.example.blog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PostService postService;

    @GetMapping
    public List<Post> getAllPost() {
        logger.info("Post controller - mapping for reading all posts");
        return postService.getAllPost();
    }

    @PostMapping
    public Post savePost(Post post) {
        logger.info("Post controller - mapping on create new post");
        return postService.savePost(post);
    }

    @PutMapping("/{id}")
    public Post editPostById(@RequestBody Post post, @PathVariable long id) {
        logger.info("Post controller - mapping on update post by id");
        return postService.savePostById(post, id);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable long id) {
        logger.info("Post controller - mapping on deleting post by id");
        postService.deletePostById(id);
    }
}