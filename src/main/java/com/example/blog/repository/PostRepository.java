package com.example.blog.repository;

import com.example.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where upper(p.title) like upper(concat('%', :title, '%'))")
    List<Post> findByTitleContainsIgnoreCase(@Param("title") String title);

    @Query("select p from Post p order by p.title")
    List<Post> findByOrderByTitleAsc();

    @Query("select p from Post p order by p.title DESC")
    List<Post> findByOrderByTitleDesc();

}