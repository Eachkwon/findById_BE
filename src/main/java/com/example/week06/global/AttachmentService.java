package com.example.week06.global;


import com.example.week06.domain.community.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final FileService fileService;
    private final String IMAGE_URL_PREFIX = "/images/";

    @Transactional
    public void savePostImage(Post post, MultipartFile postImageFile) throws IOException {
        savePostImageMethod(post, postImageFile);
    }

    @Transactional
    public void savePostImageMethod(Post post, MultipartFile postImageFile) throws IOException {

        UploadFile uploadFile = fileService.storeFile(postImageFile);
        String storeFileName = uploadFile != null ? uploadFile.getStoreFileName() : "";
        String originalFilename = uploadFile != null ? uploadFile.getOriginalFileName() : "";
        String imageURL = uploadFile != null ? IMAGE_URL_PREFIX + storeFileName : "";

        Attachment attachment = Attachment.builder()
                .imageName(storeFileName)
                .originalImageName(originalFilename)
                .imageURL(imageURL)
                .build();

        Attachment savePostImage = Attachment.createPostImage(attachment, post);
        attachmentRepository.save(savePostImage);
    }

    public List<Attachment> findByPostOrderByIdAsc(Post post) {
        return attachmentRepository.findByPostOrderByIdAsc(post);
    }

//    @Transactional
//    public void updatePostImage(Attachment attachment, MultipartFile itemImageFile) throws IOException {
//        // 기존 상품 이미지 파일이 존재하는 경우 파일 삭제
//        if (StringUtils.hasText(attachment.getImageName())) {
//            fileService.deleteFile(attachment.getImageURL());
//        }
//
//        // 새로운 이미지 파일 등록
//        UploadFile uploadFile = fileService.storeFile(itemImageFile);
//        String originalFilename = uploadFile.getOriginalFileName();
//        String storeFileName = uploadFile.getStoreFileName();
//        String imageURL = IMAGE_URL_PREFIX + storeFileName;
//
//        // 상품 이미지 파일 정보 업데이트
//        attachment.updatePostImage(originalFilename, storeFileName, imageURL);
//    }

    @Transactional
    public void deletePostImage(Attachment attachment) throws IOException {
        // 기존 이미지 파일 삭제
        String fileUploadUrl = fileService.getFullFileUploadPath(attachment.getImageName());
        fileService.deleteFile(fileUploadUrl);
        // 이미지 정보 초기화
        attachment.initPostInfo();
    }

    public Attachment findByPost(Post post) {
        return attachmentRepository.findByPost(post);
    }
}
