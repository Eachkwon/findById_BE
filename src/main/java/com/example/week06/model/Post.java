package com.example.week06.model;

import com.example.week06.dto.PostRequestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean gadaoda;

    @Column
    private boolean completed;

    @Column(nullable = false)
    private DistrictEnum district;

//    @Column(nullable = false)
//    private String fileName;
//
//    @Column(nullable = false)
//    private String filePath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //게시글을 삭제하면 달려있는 댓글 모두 삭제
    @OneToMany(mappedBy = "post_id", cascade = ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    //연관 관계
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Post(PostRequestDto requestDto, String fileName, String filePath) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
//        setUser(user);
    }

//    public void setUser(User user) {
//        this.user = user;
//        user.getPosts().add(this);
//    }

    @Builder
    public Post(String title, String content, boolean gadaoda, boolean completed, DistrictEnum district) {
        this.title = title;
        this.content = content;
        this.gadaoda = gadaoda;
        this.completed = completed;
        this.district = district;
//        this.user = user;
//        setUser(user);
    }

    public static Post createPost(Post post) {
        return Post.builder()
//                .user(user)
                .title(post.getTitle())
                .content(post.getContent())
                .gadaoda(post.isGadaoda())
                .completed(post.isCompleted())
                .district(post.getDistrict())
                .build();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    public Post(Long id, User user_Id){
        this.id = id;
        this.user_id = user_Id;
    }

}