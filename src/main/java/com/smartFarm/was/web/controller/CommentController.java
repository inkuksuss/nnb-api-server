package com.smartFarm.was.web.controller;


import com.smartFarm.was.domain.entity.Member;
import com.smartFarm.was.domain.request.comment.AddCommentForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/add/{id}")
    public ResultResponse getNewComment(HttpServletRequest httpServletRequest,
                                        @RequestBody AddCommentForm addCommentForm,
                                        @PathVariable("id") long boardId
    ) throws Exception {
        Member member = (Member) httpServletRequest.getAttribute("member");

        commentService.addComment(addCommentForm, boardId, member.getMemberId());
        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }
}
