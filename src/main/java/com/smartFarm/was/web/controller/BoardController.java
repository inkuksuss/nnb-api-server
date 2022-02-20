package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.request.AddBoardForm;
import com.smartFarm.was.domain.dto.response.Result;
import com.smartFarm.was.domain.dto.response.board.boardDetailDto;
import com.smartFarm.was.domain.dto.response.board.boardsDto;
import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.web.exception.custom.DataNotFoundException;
import com.smartFarm.was.web.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/notice")
    public ResponseEntity<Result<Map>> noticeBoards() {
        List<boardsDto> noticeBoards = boardService.getNoticeBoards();
        Map<String, List<boardsDto>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("notices", noticeBoards);
        return new ResponseEntity<>(new Result<>(stringListHashMap), HttpStatus.OK);
    }

    @GetMapping("/faq")
    public ResponseEntity<Result<Map>> faqBoards() {
        List<boardsDto> faqBoards = boardService.getFAQBoards();
        Map<String, List<boardsDto>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("faqs", faqBoards);
        return new ResponseEntity<>(new Result<>(stringListHashMap), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Result<String>> addBoard(HttpServletRequest httpServletRequest, @RequestBody AddBoardForm addBoardForm) {
        Member member = (Member) httpServletRequest.getAttribute("member");
        boardService.addBoard(addBoardForm, member.getMemberId());
        return new ResponseEntity<>(new Result<>("success"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Result<boardDetailDto>> boardDetails(HttpServletRequest httpServletRequest, @PathVariable("id") long boardId) throws DataNotFoundException {
        Member member = (Member) httpServletRequest.getAttribute("member");
        boardDetailDto boardDetailDto = boardService.findBoardDetail(boardId, member.getMemberId()).orElseThrow(() -> new DataNotFoundException("게시물이 존재하지 않습니다."));
        return new ResponseEntity<>(new Result<>(boardDetailDto), HttpStatus.OK);
    }
}
