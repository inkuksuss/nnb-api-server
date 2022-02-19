package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.request.AddBoardForm;
import com.smartFarm.was.domain.dto.response.Result;
import com.smartFarm.was.domain.dto.response.boardsDto;
import com.smartFarm.was.domain.model.Member;
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
    public ResponseEntity<Result<String>> addBoard(@RequestBody AddBoardForm addBoardForm) {
        Long memberId = 18L;
        boardService.addBoard(addBoardForm, memberId);
        return new ResponseEntity<>(new Result<>("success"), HttpStatus.OK);
    }
}
