package com.smartFarm.was.web.controller;


import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import com.smartFarm.was.domain.dto.comment.CommentDto;
import com.smartFarm.was.domain.dto.comment.DeleteCommentDto;
import com.smartFarm.was.domain.dto.comment.UpdateCommentDto;
import com.smartFarm.was.domain.dto.mapping.BoardMemberMappingDto;
import com.smartFarm.was.domain.entity.sub.Status;
import com.smartFarm.was.domain.request.comment.AddCommentForm;
import com.smartFarm.was.domain.request.comment.DeleteCommentForm;
import com.smartFarm.was.domain.request.comment.UpdateCommentForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.service.BoardService;
import com.smartFarm.was.web.service.CommentService;
import com.smartFarm.was.web.utils.MemberAuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/add")
    public ResultResponse addComment(@RequestBody AddCommentForm addCommentForm) throws Exception {

        if (MemberAuthenticationUtils.isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        long memberId = MemberAuthenticationUtils.getMemberIdByMemberAuthentication();
        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(addCommentForm.getBoardId(), memberId);

        List<CommentDto> commentDtoList;

        if (boardService.checkOwnerById(boardMemberMappingDto)) {
            AddCommentDto addCommentDto = AddCommentDto.of(addCommentForm, memberId);
            commentDtoList = commentService.addComment(addCommentDto);
        } else {
            return new ResultResponse(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), "잘못된 요청입니다.");
        }

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), commentDtoList);
    }

    @PostMapping("/update/{commentId}")
    public ResultResponse updateComment(@RequestBody UpdateCommentForm updateCommentForm, @PathVariable long commentId) throws Exception {

        if (MemberAuthenticationUtils.isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        long memberId = MemberAuthenticationUtils.getMemberIdByMemberAuthentication();
        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(updateCommentForm.getBoardId(), memberId);

        List<CommentDto> commentDtoList;

        if (boardService.checkOwnerById(boardMemberMappingDto)) {
            UpdateCommentDto updateCommentDto = new UpdateCommentDto();

            updateCommentDto.setCommentId(commentId);
            updateCommentDto.setMemberId(memberId);
            updateCommentDto.setBoardId(updateCommentForm.getBoardId());
            updateCommentDto.setCommentContent(updateCommentForm.getCommentContent());
            updateCommentDto.setCommentStatus(updateCommentForm.getCommentStatus());
            updateCommentDto.setCommentLastUpdated(new Timestamp(System.currentTimeMillis()));

            commentDtoList = commentService.updateComment(updateCommentDto);
        } else {
            return new ResultResponse(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), "잘못된 요청입니다.");
        }

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), commentDtoList);
    }

    @PostMapping("/delete/{commentId}")
    public ResultResponse deleteComment(@RequestBody DeleteCommentForm deleteCommentForm, @PathVariable long commentId) throws Exception {

        if (MemberAuthenticationUtils.isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        long memberId = MemberAuthenticationUtils.getMemberIdByMemberAuthentication();
        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(deleteCommentForm.getBoardId(), memberId);

        List<CommentDto> commentDtoList;

        if (boardService.checkOwnerById(boardMemberMappingDto)) {
            DeleteCommentDto deleteCommentDto = new DeleteCommentDto();

            deleteCommentDto.setCommentId(commentId);
            deleteCommentDto.setMemberId(memberId);
            deleteCommentDto.setBoardId(deleteCommentForm.getBoardId());
            deleteCommentDto.setCommentStatus(Status.DELETE.getStatusValue());
            deleteCommentDto.setCommentLastUpdated(new Timestamp(System.currentTimeMillis()));

            commentDtoList = commentService.deleteComment(deleteCommentDto);
        } else {
            return new ResultResponse(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), "잘못된 요청입니다.");
        }

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), commentDtoList);
    }
}

