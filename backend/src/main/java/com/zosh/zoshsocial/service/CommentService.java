package com.zosh.zoshsocial.service;

import com.zosh.zoshsocial.models.Comment;

public interface CommentService {

    Comment createComment(Comment comment, Integer postId,
                          Integer userId) throws Exception;
    Comment findCommentById(Integer commentId);
    Comment likeComment(Integer commentId, Integer userId) throws Exception;
}
