package com.smartFarm.was.web.controller;


import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import com.smartFarm.was.domain.dto.mapping.BoardMemberMappingDto;
import com.smartFarm.was.domain.request.comment.AddCommentForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.service.BoardService;
import com.smartFarm.was.web.service.CommentService;
import com.smartFarm.was.web.utils.MemberAuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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

        if (boardService.checkOwnerById(boardMemberMappingDto)) {
            AddCommentDto addCommentDto = AddCommentDto.of(addCommentForm, memberId);
            commentService.addComment(addCommentDto);
        } else {
            return new ResultResponse(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), "잘못된 요청입니다.");
        }

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), );
    }
}

