package com.example.week06.domain.community.entity;

import com.example.week06.domain.community.dto.PostRequest;
import com.example.week06.global.Timestamped;
import com.example.week06.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String lost_and_found;
    
    private String completed;

    private String district;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Post(PostRequest postRequest, User user) {
        this.title = postRequest.getTitle();
        this.content = postRequest.getContent();
        this.lost_and_found = postRequest.getFound_and_lost();
        this.completed = "uncompleted";
        this.district = postRequest.getDistrict();
        this.user = user;
    }
}