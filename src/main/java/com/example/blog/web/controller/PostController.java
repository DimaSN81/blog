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
    public List<Post> getAllPosts(@RequestParam(value = "title", required = false) String title,
                                  @RequestParam(value = "sort", required = false) String sort) {
        if (title != null) {
            logger.info("Post controller - mapping for finding all posts by title:{}", title);
            return postService.findByTitle(title);
        } else if (sort != null) {
            logger.info("Post controller - mapping for reading all posts and sorting by title{}", sort);
            return postService.findAllPost(sort);
        } else {
            logger.info("Post controller - mapping for reading all posts");
            return postService.findAllPost();
        }
    }

    @PostMapping
    public Post savePost(@RequestBody Post post) {
        logger.info("Post controller - mapping on create new post {}", post);
        return postService.savePost(post);
    }

    @PutMapping(path = "/{id}")
    public Post editPostById(@RequestBody Post post, @PathVariable long id) {
        logger.info("Post controller - mapping on update post by id {}", post);
        return postService.savePostById(post, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePostById(@PathVariable long id) {
        logger.info("Post controller - mapping on deleting post by id{}", id);
        postService.deletePostById(id);
    }
}