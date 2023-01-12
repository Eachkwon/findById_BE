package com.example.week06.global;

import com.example.week06.domain.community.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    private String imageName;     // 실제 로컬에 저장된 이미지 파일 이름 // 실제로 로컬에 저장할 이미지 파일명

    private String originalImageName;  // 업로드했던 이미지 파일 초기 이름 // 원본 이미지 파일명

    private String imageURL;      // 업로드 결과 로컬에 저장된 이미지 파일을 불러올 경로 // 이미지 조회 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

    @Builder
    public Attachment(String imageName, String originalImageName, String imageURL, Post post) {
        this.imageName = imageName;
        this.originalImageName = originalImageName;
        this.imageURL = imageURL;
        this.post = post;
    }

    public static Attachment createPostImage(Attachment itemImage, Post post) {
        return Attachment.builder()
                .imageName(itemImage.getImageName())
                .originalImageName(itemImage.getOriginalImageName())
                .imageURL(itemImage.getImageURL())
                .post(post)
                .build();
    }

    public void initPostInfo() {
        this.originalImageName = "";
        this.imageName = "";
        this.imageURL = "";
    }
}