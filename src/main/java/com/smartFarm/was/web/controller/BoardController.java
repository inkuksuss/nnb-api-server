package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.board.BoardDetailVO;
import com.smartFarm.was.domain.request.board.AddBoardForm;
import com.smartFarm.was.domain.dto.board.UpdateBoardDetailDto;
import com.smartFarm.was.domain.request.board.UpdateBoardForm;
import com.smartFarm.was.domain.response.ResultVO;
import com.smartFarm.was.domain.dto.board.BoardDetailDto;
import com.smartFarm.was.domain.response.board.BoardVO;
import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.web.service.BoardService;
import com.smartFarm.was.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
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
    public ResultVO noticeBoards() {
        List<BoardVO> noticeBoards = boardService.getNoticeBoards();

        return new ResultVO(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), noticeBoards);
    }

    @GetMapping("/faq")
    public ResultVO faqBoards() {
        List<BoardVO> faqBoards = boardService.getFAQBoards();

        return new ResultVO(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), faqBoards);
    }

    @PostMapping("/add")
    public ResultVO addBoard(HttpServletRequest httpServletRequest, @RequestBody AddBoardForm addBoardForm) {
        Member member = (Member) httpServletRequest.getAttribute("member");

        boardService.addBoard(addBoardForm, member.getMemberId());

        return new ResultVO(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    @GetMapping("/detail/{id}")
    public ResultVO boardDetails(HttpServletRequest httpServletRequest, @PathVariable("id") long boardId) throws NotFoundException {
        Member member = (Member) httpServletRequest.getAttribute("member");

        BoardDetailVO boardDetailDto = boardService.findBoardDetail(boardId, member.getMemberId());
        BoardDetailDto boardDetail = boardDetailDto.getBoardDetailDto().orElse(new BoardDetailDto());

        Map<String, Object> mapper = new HashMap<>();
        mapper.put("result", boardDetailDto.getBoardStatus().getStatusValue());
        mapper.put("detail", boardDetail);

        return new ResultVO<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), mapper);
    }

    @GetMapping("/delete/{id}")
    public ResultVO deleteBoard(HttpServletRequest httpServletRequest, @PathVariable("id") long boardId) {
        Member member = (Member) httpServletRequest.getAttribute("member");

        boardService.delete(boardId, member.getMemberId());

        return new ResultVO(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    @PostMapping("/update/{id}")
    public ResultVO updateBoard(HttpServletRequest httpServletRequest,
                                @RequestBody UpdateBoardForm updateBoardForm,
                                @PathVariable("id") long boardId) throws NotFoundException
    {
        Member member = (Member) httpServletRequest.getAttribute("member");
        UpdateBoardDetailDto updateBoardDetail = UpdateBoardDetailDto.builder()
                .boardId(boardId)
                .memberId(member.getMemberId())
                .categoryId(updateBoardForm.getCategoryId())
                .boardTitle(updateBoardForm.getBoardTitle())
                .boardContent(updateBoardForm.getBoardContent())
                .boardStatus(updateBoardForm.getBoardStatus())
                .boardLastUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        Optional<BoardDetailDto> updatedDetail = boardService.update(updateBoardDetail);
        Map<String, BoardDetailDto> mapper = new HashMap<>();
        mapper.put("detail", updatedDetail.get());
        return new ResultVO(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }
}
