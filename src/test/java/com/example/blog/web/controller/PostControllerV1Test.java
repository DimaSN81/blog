package com.example.blog.web.controller;

import com.example.blog.entity.Post;
import com.example.blog.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(PostController.class)
class PostControllerV1Test {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    @DisplayName("test on reading all posts")
    public void getAllPostTest() throws Exception {
        Post postOne = Post.builder()
                .id(1L)
                .title("Sport")
                .content("Brazilian jiu-jitsu")
                .build();
        Post postTwo = Post.builder()
                .id(2L)
                .title("Music")
                .content("Romantic")
                .build();
        when(postService.getAllPost()).thenReturn(List.of(postOne, postTwo));
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Sport"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Music"));
    }

    @Test
    @DisplayName("test on create new post")
    public void addNewPostTest() throws Exception {
        Post post = Post.builder()
                .id(3L)
                .title("Develop")
                .content("Java&Kotlin")
                .build();
        when(postService.savePost(any())).thenReturn(post);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Develop\", \"content\": \"Java&Kotlin\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Develop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Java&Kotlin"));
        verify(postService).savePost(any(Post.class));
    }

    @Test
    @DisplayName("test on editing post by id")
    public void editPostByIdTest() throws Exception {
        Post post = Post.builder()
                .id(3L)
                .title("Develop")
                .content("Java&Kotlin")
                .build();
        when(postService.savePostById(any(), anyLong())).thenReturn(post);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/posts/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Develop\", \"content\": \"Java&Kotlin\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Develop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Java&Kotlin"));
        verify(postService).savePostById(any(Post.class), anyLong());
    }

    @Test
    @DisplayName("test on deleting post by id")
    public void deletePostByIdTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/posts/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(postService).deletePostById(anyLong());
    }
}