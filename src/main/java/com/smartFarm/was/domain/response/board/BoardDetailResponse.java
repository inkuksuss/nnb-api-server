package com.smartFarm.was.domain.response.board;

import com.smartFarm.was.domain.dto.board.BoardDetailDto;
import com.smartFarm.was.domain.entity.sub.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@Getter
@RequiredArgsConstructor
public class BoardDetailResponse {

    private final Status boardStatus;
    private final Optional<BoardDetailDto> boardDetailDto;
}
