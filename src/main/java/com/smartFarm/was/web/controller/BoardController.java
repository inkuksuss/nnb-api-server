package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.response.Result;
import com.smartFarm.was.domain.model.Board;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.web.repository.MemberRepository;
import com.smartFarm.was.web.service.BoardService;
import com.smartFarm.was.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<Result<List<Board>>> boards() {
        List<Board> allBoard = boardService.getAllBoard();
        return new ResponseEntity<>(new Result<>(allBoard), HttpStatus.OK);
    }

    @PostMapping("/boards/add")
    public ResponseEntity<Result<String>> addBoard() {
        return new ResponseEntity<>(new Result<>("success"), HttpStatus.OK);
    }
}
