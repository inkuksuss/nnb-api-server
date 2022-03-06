package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.request.comment.AddCommentForm;
import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import com.smartFarm.was.domain.model.sub.Status;
import com.smartFarm.was.web.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void addComment() {
        AddCommentForm addCommentForm = new AddCommentForm("fffaaaf", Status.PUBLIC.getStatusValue());
        AddCommentDto comment = AddCommentDto.of(addCommentForm, 33l, 34l);
        commentRepository.add(comment);
        log.info("add = {}", comment.toString());

    }
}