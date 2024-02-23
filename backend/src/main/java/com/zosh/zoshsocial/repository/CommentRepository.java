package com.zosh.zoshsocial.repository;

import com.zosh.zoshsocial.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
