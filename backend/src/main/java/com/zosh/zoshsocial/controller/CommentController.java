package com.zosh.zoshsocial.controller;

import com.zosh.zoshsocial.models.Comment;
import com.zosh.zoshsocial.models.User;
import com.zosh.zoshsocial.service.CommentService;
import com.zosh.zoshsocial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/{postId}")
    public Comment createComment(
            @RequestBody Comment comment,
            @RequestHeader("Authorization") String jwt,
            @PathVariable("postId") Integer postId
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);

        Comment createdComment = commentService.createComment(comment, postId, user.getId());
        return createdComment;
    }

    @PutMapping("/like/{commentId}")
    public Comment likeComment(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("commentId") Integer commentId
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);

        Comment likedComment = commentService.likeComment(commentId, user.getId());
        return likedComment;
    }
}
