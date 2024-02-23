package com.zosh.zoshsocial.service;

import com.zosh.zoshsocial.models.Comment;
import com.zosh.zoshsocial.models.Post;
import com.zosh.zoshsocial.models.User;
import com.zosh.zoshsocial.repository.CommentRepository;
import com.zosh.zoshsocial.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) {
        Optional<Comment> opt = commentRepository.findById(commentId);
        if (opt.isEmpty()){
            throw new RuntimeException("Comment not exists");
        }
        return opt.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if (!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else {
            comment.getLiked().remove(user);
        }
        return commentRepository.save(comment);
    }
}
