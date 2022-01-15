package com.smartFarm.was.web.controller;

import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.web.repository.MemberRepository;
import com.smartFarm.was.web.service.BoardService;
import com.smartFarm.was.web.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class BoardController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    public BoardController(MemberService memberService, MemberRepository memberRepository, BoardRepository boardRepository, BoardService boardService) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
        this.boardService = boardService;
    }
}
