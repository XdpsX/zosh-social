package com.zosh.zoshsocial.controller;

import com.zosh.zoshsocial.models.Post;
import com.zosh.zoshsocial.models.User;
import com.zosh.zoshsocial.response.ApiResponse;
import com.zosh.zoshsocial.service.PostService;
import com.zosh.zoshsocial.service.UserService;
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
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Post> createPost(
                                            @RequestHeader("Authorization") String jwt,
                                            @RequestBody Post post
                                           ) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post createdPost = postService.createNewPost(post, reqUser.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(
            @PathVariable Integer postId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId, reqUser.getId());
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

    @PutMapping("/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(
            @PathVariable Integer postId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likePostHandler(
            @PathVariable Integer postId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
}
