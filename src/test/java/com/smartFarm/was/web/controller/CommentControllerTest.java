//package com.smartFarm.was.web.controller;
//
//import com.smartFarm.was.domain.dto.comment.*;
//import com.smartFarm.was.domain.entity.sub.Status;
//import com.smartFarm.was.domain.request.comment.AddCommentForm;
//import com.smartFarm.was.domain.request.comment.DeleteCommentForm;
//import com.smartFarm.was.domain.request.comment.UpdateCommentForm;
//import com.smartFarm.was.web.service.CommentService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.List;
//
//
//@SpringBootTest
//@Slf4j
//class CommentControllerTest {
//
//    @Autowired
//    CommentService commentService;
//
//    private final long boardId = 48l;
//    private final long memberId = 35l;
//
//    private final long comment1 = 82l;
//    private final long comment2 = 83l;
//    private final long comment3 = 84l;
//
//
//    @Test
//    void addComment() {
//        AddCommentForm addCommentForm = new AddCommentForm(boardId, "test3", Status.PUBLIC.getStatusValue());
//        AddCommentDto of = AddCommentDto.of(addCommentForm, memberId);
//
//        try {
//            List<CommentDto> commentDtoList = commentService.addComment(of);
//            log.info(commentDtoList.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void updateComment() {
//        UpdateCommentForm updateCommentForm = new UpdateCommentForm(boardId, "aaaa", Status.PRIVATE.getStatusValue());
//
//        UpdateCommentDto updateCommentDto = new UpdateCommentDto();
//
//        updateCommentDto.setCommentId(comment1);
//        updateCommentDto.setMemberId(memberId);
//        updateCommentDto.setBoardId(updateCommentForm.getBoardId());
//        updateCommentDto.setCommentContent(updateCommentForm.getCommentContent());
//        updateCommentDto.setCommentStatus(updateCommentForm.getCommentStatus());
//        updateCommentDto.setCommentLastUpdated(new Timestamp(System.currentTimeMillis()));
//        try {
//            commentService.updateComment(updateCommentDto);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void deleteComment() {
//        DeleteCommentForm deleteCommentForm = new DeleteCommentForm(boardId);
//
//        DeleteCommentDto deleteCommentDto = new DeleteCommentDto();
//        deleteCommentDto.setCommentId(comment3);
//        deleteCommentDto.setMemberId(memberId);
//        deleteCommentDto.setBoardId(deleteCommentForm.getBoardId());
//        deleteCommentDto.setCommentStatus(Status.DELETE.getStatusValue());
//        deleteCommentDto.setCommentLastUpdated(new Timestamp(System.currentTimeMillis()));
//        try {
//            commentService.deleteComment(deleteCommentDto);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    void getComment() {
//
//        GetCommentDto getCommentDto = GetCommentDto.forOwner(boardId);
//        try {
//            List<CommentDto> comment = commentService.getCommentList(getCommentDto);
//            for (CommentDto commentDto : comment) {
//                log.info("comment = {}", commentDto);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}