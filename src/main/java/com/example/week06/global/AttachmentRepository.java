package com.example.week06.global;

import com.example.week06.global.Attachment;
import com.example.week06.domain.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    //    List<CoffeeImg> findByPostOrderByCoffeeImgIdAsc(Post post);
    List<Attachment> findByPostOrderByIdAsc(Post post);

    Attachment findByPost(Post post);

}
