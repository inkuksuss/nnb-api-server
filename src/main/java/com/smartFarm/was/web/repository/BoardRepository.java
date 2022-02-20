package com.smartFarm.was.web.repository;


import com.smartFarm.was.domain.dto.response.board.boardDetailDto;
import com.smartFarm.was.domain.dto.response.board.boardsDto;
import com.smartFarm.was.domain.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface BoardRepository {

    List<boardsDto> findAllNotice();
    List<boardsDto> findAllFAQ();
    void add(Board board);
    Optional<boardDetailDto> findByIdDetail(long boardId);
}
