package com.zosh.zoshsocial.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String caption;
    private String image;
    private String video;
    private User user;
    private LocalDateTime createdAt;

    private List<User> liked = new ArrayList<>();
}
