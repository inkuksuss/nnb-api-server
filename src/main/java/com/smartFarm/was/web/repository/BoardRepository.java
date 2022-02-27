package com.smartFarm.was.web.repository;


import com.smartFarm.was.domain.dto.request.board.UpdateBoardForm;
import com.smartFarm.was.domain.dto.response.board.BoardDetailDto;
import com.smartFarm.was.domain.dto.response.board.BoardsDto;
import com.smartFarm.was.domain.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface BoardRepository {

    List<BoardsDto> findAllNotice();

    List<BoardsDto> findAllFAQ();

    void add(Board board);

    Optional<BoardDetailDto> findByIdDetail(long boardId);

    int deleteByIds(long boardId, long memberId);

    int updateByUpdateForm(UpdateBoardForm updateBoardForm);
}
