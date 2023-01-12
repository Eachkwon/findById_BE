package com.example.week06.domain.community.entity;

import com.example.week06.domain.community.dto.PostRequestDto;

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
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String gadaoda;

    @Column
    private String completed = "uncompleted";

    @Column(nullable = false)
    private String district;

//    @Column(nullable = false)
//    private String fileName;
//
//    @Column(nullable = false)
//    private String filePath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //게시글을 삭제하면 달려있는 댓글 모두 삭제
    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
    private List<Comment> comments;


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
    public Post(String title, String content, String gadaoda, String completed, User user) {
        this.title = title;
        this.content = content;
        this.gadaoda = gadaoda;
        this.completed = completed;
        this.district = district;
//        this.user = user;
//        setUser(user);
    }

//    public static Post createPost(Post post, MultipartFile file, Long userId) {
//        return Post.builder()
//                .title(post.getTitle())
//                .content(post.getContent())
//                .gadaoda(post.getGadaoda())
//                .completed(post.getCompleted())
//                .district(post.getDistrict())
//                .build();
//    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user_id;

    public Post(Long id, User user_Id){
        this.id = id;
        this.user_id = user_Id;
    }

}