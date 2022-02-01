package com.smartFarm.was.web.repository;


import com.smartFarm.was.domain.dto.response.boardsDto;
import com.smartFarm.was.domain.model.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {

    List<boardsDto> getAllNotice();
    List<boardsDto> getAllFAQ();
    void save(Board board);
}
