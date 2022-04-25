package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.board.AddBoardDto;
import com.smartFarm.was.domain.dto.mapping.BoardMemberMappingDto;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.request.board.AddBoardForm;
import com.smartFarm.was.domain.dto.board.UpdateBoardDto;
import com.smartFarm.was.domain.request.board.UpdateBoardForm;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.domain.response.board.BoardResponse;
import com.smartFarm.was.domain.response.board.UpdateBoardResponse;
import com.smartFarm.was.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import static com.smartFarm.was.web.utils.FormValidationUtils.*;
import static com.smartFarm.was.web.utils.MemberAuthenticationUtils.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/notice")
    public ResultResponse<List<BoardResponse>> getNoticeBoards() throws Exception {
        return boardService.getNoticeBoards();
    }

    @GetMapping("/faq")
    public ResultResponse<List<BoardResponse>> getFaqBoards() throws Exception {
        return boardService.getFAQBoards();
    }

    @PostMapping("/add")
    public ResultResponse<Long> addBoard(@RequestBody @Validated AddBoardForm addBoardForm) throws Exception {

        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        AddBoardDto addBoardDto = AddBoardDto.of(addBoardForm, getMemberIdByMemberAuthentication());

        return boardService.addBoard(addBoardDto);
    }

    @GetMapping("/detail/{boardId}")
    public ResultResponse<BoardResponse> getBoard(@PathVariable Long boardId) throws Exception {

        if (illegalLongValue(boardId)) {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.PAGE_NOT_FOUND.getCode(), ResultCode.PAGE_NOT_FOUND.getMessage());
        }

        return boardService.getBoard(boardId);
    }

    @GetMapping("/delete/{boardId}")
    public ResultResponse<Void> deleteBoard(@PathVariable @Validated Long boardId) throws Exception {

        if (illegalLongValue(boardId)) {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.PAGE_NOT_FOUND.getCode(), ResultCode.PAGE_NOT_FOUND.getMessage());
        }

        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        long memberId = getMemberIdByMemberAuthentication();
        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(boardId, memberId);

        boolean isOwner = boardService.checkOwnerById(boardMemberMappingDto);

        if (isOwner) {
            return boardService.deleteBoard(boardId);
        } else {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), "잘못된 요청입니다.");
        }
    }

    @PostMapping("/update/{boardId}")
    public ResultResponse<Long> updateBoard(@RequestBody @Validated UpdateBoardForm updateBoardForm, @PathVariable Long boardId) throws Exception {

        if (illegalLongValue(boardId)) {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.PAGE_NOT_FOUND.getCode(), ResultCode.PAGE_NOT_FOUND.getMessage());
        }

        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), "로그인 후 이용 가능한 서비스입니다.");
        }

        BoardMemberMappingDto boardMemberMappingDto = BoardMemberMappingDto.of(boardId, getMemberIdByMemberAuthentication());

        boolean isOwner = boardService.checkOwnerById(boardMemberMappingDto);

        if (isOwner) {

            UpdateBoardDto updateBoardDto = UpdateBoardDto.builder()
                    .boardId(boardId)
                    .memberId(getMemberIdByMemberAuthentication())
                    .categoryId(updateBoardForm.getCategoryId())
                    .boardTitle(updateBoardForm.getBoardTitle())
                    .boardContent(updateBoardForm.getBoardContent())
                    .boardStatus(updateBoardForm.getBoardStatus())
                    .boardLastUpdated(new Timestamp(System.currentTimeMillis()))
                    .build();

            return boardService.updateBoard(updateBoardDto);

        } else {

            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), "잘못된 요청입니다.");

        }
    }
}
