package com.zosh.zoshsocial.controller;

import com.zosh.zoshsocial.models.Post;
import com.zosh.zoshsocial.response.ApiResponse;
import com.zosh.zoshsocial.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,
                                           @PathVariable Integer userId) throws Exception {
        Post createdPost = postService.createNewPost(post, userId);
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(
            @PathVariable Integer postId,
            @PathVariable Integer userId
    ) throws Exception {
        String message = postService.deletePost(postId, userId);
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(
            @PathVariable Integer postId
    ) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(
            @PathVariable Integer userId
    ) {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(
            @PathVariable Integer postId,
            @PathVariable Integer userId
    ) throws Exception {
        Post post = postService.savedPost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(
            @PathVariable Integer postId,
            @PathVariable Integer userId
    ) throws Exception {
        Post post = postService.likePost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
}
