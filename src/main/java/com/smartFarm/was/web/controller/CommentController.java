package com.smartFarm.was.web.controller;


import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import com.smartFarm.was.domain.dto.comment.CommentDto;
import com.smartFarm.was.domain.dto.comment.DeleteCommentDto;
import com.smartFarm.was.domain.dto.comment.UpdateCommentDto;
import com.smartFarm.was.domain.dto.mapping.BoardMemberMappingDto;
import com.smartFarm.was.domain.request.comment.AddCommentForm;
import com.smartFarm.was.domain.request.comment.DeleteCommentForm;
import com.smartFarm.was.domain.request.comment.UpdateCommentForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.service.impl.BoardServiceImpl;
import com.smartFarm.was.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import static com.smartFarm.was.web.utils.FormValidationUtils.*;
import static com.smartFarm.was.web.utils.MemberAuthenticationUtils.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final BoardServiceImpl boardService;

    @PostMapping("/add")
    public ResultResponse<List<CommentDto>> addComment(@RequestBody AddCommentForm addCommentForm) throws Exception {

        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        long memberId = getMemberIdByMemberAuthentication();
        AddCommentDto addCommentDto;
        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(addCommentForm.getBoardId(), memberId);

        if (boardService.checkOwnerById(boardMemberMappingDto)) {
            addCommentDto = AddCommentDto.of(addCommentForm, memberId, true);
        } else {
            addCommentDto = AddCommentDto.of(addCommentForm, memberId, false);
        }

        return commentService.addComment(addCommentDto);
    }

    @PostMapping("/update/{commentId}")
    public ResultResponse<List<CommentDto>> updateComment(@RequestBody UpdateCommentForm updateCommentForm, @PathVariable Long commentId) throws Exception {

        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(updateCommentForm.getBoardId(), getMemberIdByMemberAuthentication());
        UpdateCommentDto updateCommentDto = createUpdateDto(updateCommentForm, commentId, boardMemberMappingDto);

        return commentService.updateComment(updateCommentDto);
    }

    private UpdateCommentDto createUpdateDto(UpdateCommentForm updateCommentForm, Long commentId, BoardMemberMappingDto boardMemberMappingDto) throws Exception {
        UpdateCommentDto updateCommentDto = new UpdateCommentDto();

        updateCommentDto.setCommentId(commentId);
        updateCommentDto.setMemberId(getMemberIdByMemberAuthentication());
        updateCommentDto.setBoardId(updateCommentForm.getBoardId());
        updateCommentDto.setCommentContent(updateCommentForm.getCommentContent());
        updateCommentDto.setCommentStatus(updateCommentForm.getCommentStatus());
        updateCommentDto.setCommentLastUpdated(new Timestamp(System.currentTimeMillis()));
        updateCommentDto.setOwnerFlag(boardService.checkOwnerById(boardMemberMappingDto));
        return updateCommentDto;
    }

    @PostMapping("/delete/{commentId}")
    public ResultResponse<List<CommentDto>> deleteComment(@RequestBody DeleteCommentForm deleteCommentForm, @PathVariable Long commentId) throws Exception {

        Long boardId = deleteCommentForm.getBoardId();

        if (illegalLongValue(boardId) || illegalLongValue(commentId)) {
            return new ResultResponse<>(HttpStatus.NOT_FOUND, ResultCode.PAGE_NOT_FOUND.getCode(), "잘못된 경로입니다. ");
        }

        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(boardId, getMemberIdByMemberAuthentication());
        DeleteCommentDto deleteCommentDto = createDeleteDto(commentId, boardId, boardMemberMappingDto);

        return commentService.deleteComment(deleteCommentDto);
    }

    private DeleteCommentDto createDeleteDto(Long commentId, Long boardId, BoardMemberMappingDto boardMemberMappingDto) throws Exception {
        DeleteCommentDto deleteCommentDto = new DeleteCommentDto();
        deleteCommentDto.setCommentId(commentId);
        deleteCommentDto.setBoardId(boardId);
        deleteCommentDto.setMemberId(getMemberIdByMemberAuthentication());
        deleteCommentDto.setCommentLastUpdated(new Timestamp(System.currentTimeMillis()));
        deleteCommentDto.setStateDel('Y');
        deleteCommentDto.setOwnerFlag(boardService.checkOwnerById(boardMemberMappingDto));
        return deleteCommentDto;
    }
}

