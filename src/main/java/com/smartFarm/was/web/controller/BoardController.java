package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.mapping.BoardMemberMappingDto;
import com.smartFarm.was.domain.entity.Board;
import com.smartFarm.was.domain.entity.sub.Status;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.board.AddBoardResponse;
import com.smartFarm.was.domain.response.board.BoardDetailResponse;
import com.smartFarm.was.domain.request.board.AddBoardForm;
import com.smartFarm.was.domain.dto.board.UpdateBoardDto;
import com.smartFarm.was.domain.request.board.UpdateBoardForm;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.domain.response.board.BoardResponse;
import com.smartFarm.was.domain.response.board.UpdateBoardResponse;
import com.smartFarm.was.web.service.BoardService;
import com.smartFarm.was.web.service.CommentService;
import com.smartFarm.was.web.utils.MemberAuthenticationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


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

        Board board = Board.of(addBoardForm, MemberAuthenticationUtils.getMemberIdByMemberAuthentication());

        long boardId = boardService.addBoard(board);

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), new AddBoardResponse(boardId));
    }

    @GetMapping("/detail/{boardId}")
    public ResultResponse getBoardDetail(@PathVariable long boardId) throws Exception {

        BoardDetailResponse boardDetailResponse = boardService.getBoardDetail(boardId);

        if (boardDetailResponse.getBoardStatus().equals(Status.DELETE.getStatusValue())) {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.DENIED.getCode(), "삭제된 게시물입니다.");
        }

        if (boardDetailResponse.getBoardStatus().equals(Status.PRIVATE.getStatusValue())) {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.DENIED.getCode(), "비공개 게시물입니다.");
        }

        return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), boardDetailResponse);
    }

    @GetMapping("/delete/{boardId}")
    public ResultResponse deleteBoard(@PathVariable long boardId) throws Exception {

        if (MemberAuthenticationUtils.isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(boardId, MemberAuthenticationUtils.getMemberIdByMemberAuthentication());

        if(boardService.checkOwnerById(boardMemberMappingDto)) {
            boardService.deleteBoard(boardId);
        } else {
            return new ResultResponse(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), "잘못된 요청입니다.");
        }

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    @PostMapping("/update/{boardId}")
    public ResultResponse updateBoard(@RequestBody UpdateBoardForm updateBoardForm, @PathVariable long boardId) throws Exception {

        if (MemberAuthenticationUtils.isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(boardId, MemberAuthenticationUtils.getMemberIdByMemberAuthentication());

        if (boardService.checkOwnerById(boardMemberMappingDto)) {

            UpdateBoardDto updateBoardDto = UpdateBoardDto.builder()
                    .boardId(boardId)
                    .memberId(MemberAuthenticationUtils.getMemberIdByMemberAuthentication())
                    .categoryId(updateBoardForm.getCategoryId())
                    .boardTitle(updateBoardForm.getBoardTitle())
                    .boardContent(updateBoardForm.getBoardContent())
                    .boardStatus(updateBoardForm.getBoardStatus())
                    .boardLastUpdated(new Timestamp(System.currentTimeMillis()))
                    .build();

            boardService.updateBoard(updateBoardDto);
        } else {
            return new ResultResponse(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), "잘못된 요청입니다.");
        }

        return new ResultResponse(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), new UpdateBoardResponse(boardId));
    }
}
