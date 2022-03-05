package com.smartFarm.was.web.controller;


import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.domain.request.comment.AddCommentForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultVO;
import com.smartFarm.was.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/add/{id}")
    public ResultVO getNewComment(HttpServletRequest httpServletRequest,
                               @RequestBody AddCommentForm addCommentForm,
                               @PathVariable("id") long boardId
    ) {
        Member member = (Member) httpServletRequest.getAttribute("member");

        commentService.addComment(addCommentForm, boardId, member.getMemberId());
        return new ResultVO(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }
}
