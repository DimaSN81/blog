package com.example.blog.service;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public Comment getById(Long id) {
        log.info("CommentService - get comment by id {}", id);
        return commentRepository.getById(id);
    }

    @Override
    public List<Comment> getByPostId(Long postId) {
        log.info("CommentService - get all comments by postId {}", postId);
        return commentRepository.findByPost_IdIs(postId);
    }

    @Override
    public Comment addByPostId(Comment comment, Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        comment.setPost(post.get());
        Comment savedComment = commentRepository.save(comment);
        log.info("CommentService - add new comment by postId {}", postId);
        return savedComment;
    }
}
