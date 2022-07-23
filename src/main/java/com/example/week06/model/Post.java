package com.example.week06.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Table(name = "POST")
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "post_id")
    private Long id;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageURL;

    @Column(nullable = false)
    private boolean odagada;

    @Column
    private boolean completed;

    //연관 관계
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    // 게시글 수정
    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContents(String content) {
        this.content = content;
    }

    public void updateImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    //게시글을 삭제하면 달려있는 댓글 모두 삭제
    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}