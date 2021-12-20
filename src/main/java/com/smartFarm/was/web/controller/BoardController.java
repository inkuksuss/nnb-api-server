package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.repository.BoardRepository;
import com.smartFarm.was.domain.repository.MemberRepository;
import com.smartFarm.was.domain.service.BoardService;
import com.smartFarm.was.domain.service.MemberService;
import com.smartFarm.was.web.config.jwt.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class BoardController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final TokenProvider tokenProvider;

    public BoardController(MemberService memberService, MemberRepository memberRepository, BoardRepository boardRepository, BoardService boardService, TokenProvider tokenProvider) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
        this.boardService = boardService;
        this.tokenProvider = tokenProvider;
    }

//    @GetMapping("/boards")
//    public BoardDto board() {
//        return boardRepository.getAll();
//    }
}
