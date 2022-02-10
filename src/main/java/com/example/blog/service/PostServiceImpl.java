package com.example.blog.service;

import com.example.blog.entity.Post;
import com.example.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Post> getAllPost() {
        logger.info("Post service - getting all posts");
        return postRepository.findAll();
    }

    @Override
    public Post savePost(Post post) {
        logger.info("Post service - save new post");
        return postRepository.save(post);
    }

    @Override
    public Post savePostById(Post post, long postId) {
        logger.info("Post service - update post by id");
        post.setId(postId);
        return postRepository.save(post);
    }

    @Override
    public void deletePostById(long id) {
        logger.info("Post service - deleting post by id");
        postRepository.deleteById(id);
    }
}