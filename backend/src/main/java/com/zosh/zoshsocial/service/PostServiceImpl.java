package com.zosh.zoshsocial.service;

import com.zosh.zoshsocial.models.Post;
import com.zosh.zoshsocial.models.User;
import com.zosh.zoshsocial.repository.PostRepository;
import com.zosh.zoshsocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getUser().getId() != user.getId()){
            throw new Exception("You can't delete another users post");
        }
        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt = postRepository.findById(postId);
        if (opt.isEmpty()){
            throw new Exception("Post not found with id = " + postId);
        }
        return opt.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
