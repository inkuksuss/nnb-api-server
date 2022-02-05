package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.request.AddBoardForm;
import com.smartFarm.was.domain.dto.response.Result;
import com.smartFarm.was.domain.dto.response.boardsDto;
import com.smartFarm.was.domain.model.Board;
import com.smartFarm.was.web.config.security.context.MemberContext;
import com.smartFarm.was.web.config.security.provider.TokenProvider;
import com.smartFarm.was.web.repository.BoardRepository;
import com.smartFarm.was.web.repository.MemberRepository;
import com.smartFarm.was.web.service.BoardService;
import com.smartFarm.was.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final TokenProvider tokenProvider;

    @GetMapping("/boards/notice")
    public ResponseEntity<Result<Map>> noticeBoards() {
        List<boardsDto> noticeBoards = boardService.getNoticeBoards();
        Map<String, List<boardsDto>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("notices", noticeBoards);
        return new ResponseEntity<>(new Result<>(stringListHashMap), HttpStatus.OK);
    }

    @GetMapping("/boards/faq")
    public ResponseEntity<Result<Map>> faqBoards() {
        List<boardsDto> faqBoards = boardService.getFAQBoards();
        Map<String, List<boardsDto>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("faqs", faqBoards);
        return new ResponseEntity<>(new Result<>(stringListHashMap), HttpStatus.OK);
    }

    @PostMapping("/boards/add")
    public ResponseEntity<Result<String>> addBoard(@RequestBody AddBoardForm addBoardForm) {
        Long memberId = 18L;
        boardService.addBoard(addBoardForm, memberId);
        return new ResponseEntity<>(new Result<>("success"), HttpStatus.OK);
    }
    // TODO 유저 아이디 값 가져오기
    @PostMapping("/test")
    public Object test(HttpServletRequest request) {
        Object id = request.getAttribute("id");
        System.out.println(id);
        return id;
    }
}
