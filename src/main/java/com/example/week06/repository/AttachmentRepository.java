package com.example.week06.repository;

import com.example.week06.model.Attachment;
import com.example.week06.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    //    List<CoffeeImg> findByPostOrderByCoffeeImgIdAsc(Post post);
    List<Attachment> findByPostOrderByIdAsc(Post post);

    Attachment findByPost(Post post);

}
