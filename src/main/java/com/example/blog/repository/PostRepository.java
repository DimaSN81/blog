package com.example.blog.repository;

import com.example.blog.entity.Post;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where upper(p.title) like upper(concat('%', :title, '%'))")
    List<Post> findByTitleContainsIgnoreCase(@Param("title") String title);

    @Query("select p from Post p order by p.title")
    List<Post> findByOrderByTitleAsc();

    @Query("select p from Post p order by p.title DESC")
    List<Post> findByOrderByTitleDesc();

    @Query("select p from Post p where p.star = true")
    List<Post> findByStarTrue();

    @Modifying
    @Query(value = "update Post set star = true where id = :id returning *", nativeQuery = true)
    Post setStarById(@Param("id") Long id);

    @Modifying
    @Query(value = "update Post set star = false where id = :id returning *", nativeQuery = true)
    Post deleteStarById(@Param("id") Long id);

}