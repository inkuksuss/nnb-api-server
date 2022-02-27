package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.request.board.AddBoardForm;
import com.smartFarm.was.domain.dto.request.board.UpdateBoardForm;
import com.smartFarm.was.domain.dto.response.Result;
import com.smartFarm.was.domain.dto.response.board.BoardDetailDto;
import com.smartFarm.was.domain.dto.response.board.BoardsDto;
import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/notice")
    public ResponseEntity<Result<Map>> noticeBoards() {
        List<BoardsDto> noticeBoards = boardService.getNoticeBoards();
        Map<String, List<BoardsDto>> mapper = new HashMap<>();
        mapper.put("notices", noticeBoards);
        return new ResponseEntity<>(new Result<>(mapper), HttpStatus.OK);
    }

    @GetMapping("/faq")
    public ResponseEntity<Result<Map>> faqBoards() {
        List<BoardsDto> faqBoards = boardService.getFAQBoards();

        Map<String, List<BoardsDto>> mapper = new HashMap<>();
        mapper.put("faqs", faqBoards);

        return new ResponseEntity<>(new Result<>(mapper), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Result<String>> addBoard(HttpServletRequest httpServletRequest, @RequestBody AddBoardForm addBoardForm) {
        Member member = (Member) httpServletRequest.getAttribute("member");

        boardService.addBoard(addBoardForm, member.getMemberId());

        return new ResponseEntity<>(new Result<>("success"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Result<Map<String, Object>>> boardDetails(HttpServletRequest httpServletRequest, @PathVariable("id") long boardId) throws NotFoundException {
        Member member = (Member) httpServletRequest.getAttribute("member");

        BoardService.DetailResult boardDetailDto = boardService.findBoardDetail(boardId, member.getMemberId());
        BoardDetailDto boardDetail = boardDetailDto.getBoardDetailDto().orElse(new BoardDetailDto());

        Map<String, Object> mapper = new HashMap<>();
        mapper.put("result", boardDetailDto.getBoardStatus().getStatusValue());
        mapper.put("detail", boardDetail);

        return new ResponseEntity<>(new Result<>(mapper), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Result<String>> deleteBoard(HttpServletRequest httpServletRequest, @PathVariable("id") long boardId) {
        Member member = (Member) httpServletRequest.getAttribute("member");

        boardService.delete(boardId, member.getMemberId());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Result<String>> updateBoard(HttpServletRequest httpServletRequest,
                                                      @RequestParam("categoryId") long categoryId,
                                                      @RequestParam("boardTitle") String boardTitle,
                                                      @RequestParam("boardContent") String boardContent,
                                                      @RequestParam("boardStatus") String boardStatus,
                                                      @PathVariable("id") long boardId)
    {
        Member member = (Member) httpServletRequest.getAttribute("member");
        UpdateBoardForm updateBoardForm = UpdateBoardForm.builder()
                .boardId(boardId)
                .memberId(member.getMemberId())
                .categoryId(categoryId)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardStatus(boardStatus)
                .boardLastUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        boardService.update(updateBoardForm);
        return new ResponseEntity<>()
    }

}
