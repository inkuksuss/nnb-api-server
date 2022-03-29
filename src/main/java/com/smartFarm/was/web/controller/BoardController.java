package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.entity.sub.Status;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.board.AddBoardResponse;
import com.smartFarm.was.domain.response.board.BoardDetailResponse;
import com.smartFarm.was.domain.request.board.AddBoardForm;
import com.smartFarm.was.domain.dto.board.UpdateBoardDto;
import com.smartFarm.was.domain.request.board.UpdateBoardForm;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.domain.dto.board.BoardDetailDto;
import com.smartFarm.was.domain.response.board.BoardResponse;
import com.smartFarm.was.domain.entity.Member;
import com.smartFarm.was.web.service.BoardService;
import com.smartFarm.was.web.service.CommentService;
import com.smartFarm.was.web.utils.MemberAuthenticationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/notice")
    public ResultResponse getNoticeBoards() throws Exception {
        List<BoardResponse> boardResponsesList = boardService.getNoticeBoards();

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), boardResponsesList);
    }

    @GetMapping("/faq")
    public ResultResponse getFaqBoards() throws Exception {
        List<BoardResponse> boardResponsesList = boardService.getFAQBoards();

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), boardResponsesList);
    }

    @PostMapping("/add")
    public ResultResponse addBoard(@RequestBody AddBoardForm addBoardForm) throws Exception {

        if (MemberAuthenticationUtils.isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        Member member = MemberAuthenticationUtils.getMemberAuthentication();

        long boardId = boardService.addBoard(addBoardForm, member.getMemberId());

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), new AddBoardResponse(boardId));
    }

    @GetMapping("/detail/{id}")
    public ResultResponse getBoardDetail(@PathVariable("id") long boardId) throws Exception {

        if (MemberAuthenticationUtils.isMember()) {
            Member member = MemberAuthenticationUtils.getMemberAuthentication();

            BoardDetailResponse boardDetailResponse = boardService.getBoardDetail(boardId, member.getMemberId());

            if (boardDetailResponse.getBoardStatus().equals(Status.DELETE.getStatusValue())) {
                return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.DENIED.getCode(), "삭제된 게시물입니다.");
            }

            if (boardDetailResponse.getBoardStatus().equals(Status.PRIVATE.getStatusValue())) {
                return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.DENIED.getCode(), "비공개 게시물입니다.");
            }

            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), boardDetailResponse);
        }
    }

    @GetMapping("/delete/{id}")
    public ResultResponse removeBoard(HttpServletRequest httpServletRequest, @PathVariable("id") long boardId) throws Exception {
        Member member = (Member) httpServletRequest.getAttribute("member");

        boardService.removeBoard(boardId, member.getMemberId());

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    @PostMapping("/update/{id}")
    public ResultResponse changeBoard(HttpServletRequest httpServletRequest,
                                      @RequestBody UpdateBoardForm updateBoardForm,
                                      @PathVariable("id") long boardId) throws Exception
    {
        Member member = (Member) httpServletRequest.getAttribute("member");

        UpdateBoardDto updateBoardDto = UpdateBoardDto.builder()
                .boardId(boardId)
                .memberId(member.getMemberId())
                .categoryId(updateBoardForm.getCategoryId())
                .boardTitle(updateBoardForm.getBoardTitle())
                .boardContent(updateBoardForm.getBoardContent())
                .boardStatus(updateBoardForm.getBoardStatus())
                .boardLastUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        Optional<BoardDetailDto> updatedDetail = boardService.changeBoard(updateBoardDto);

        Map<String, BoardDetailDto> mapper = new HashMap<>();
        mapper.put("detail", updatedDetail.get());

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }
}
