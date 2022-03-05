package com.smartFarm.was.web.repository;


import com.smartFarm.was.domain.dto.board.UpdateBoardDetailDto;
import com.smartFarm.was.domain.dto.board.BoardDetailDto;
import com.smartFarm.was.domain.response.board.BoardVO;
import com.smartFarm.was.domain.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface BoardRepository {

    List<BoardVO> findAllNotice();

    List<BoardVO> findAllFAQ();

    void add(Board board);

    Optional<BoardDetailDto> findByIdDetail(long boardId);

    int deleteByIds(long boardId, long memberId);

    int updateByUpdateForm(UpdateBoardDetailDto updateBoardDetail);
}
