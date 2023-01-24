package com.example.week06.domain.community.service;

import com.example.week06.domain.community.entity.Comment;
import com.example.week06.domain.community.entity.Post;
import com.example.week06.domain.user.entity.User;
import com.example.week06.domain.community.repository.CommentRepository;
import com.example.week06.domain.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public String createComment(String content, Long postId, User user){
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 게시물입니다.")
        );
        Comment comment = new Comment(content, user, post);

        commentRepository.save(comment);

        return "댓글 작성에 성공하였습니다.";
    }

    public String deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글이 존재하지 않습니다.")
        );

        if(!comment.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글에 대한 권한이 없습니다.");
        }

        commentRepository.deleteById(commentId);

        return "댓글 삭제에 성공하였습니다.";
    }

    public String putComment(Long commentId, String content, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글이 존재하지 않습니다."));

        if(!comment.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글에 대한 권한이 없습니다.");
        }

        comment.update(content);
        commentRepository.save(comment);

        return "댓글 수정에 성공하였습니다.";
    }

}