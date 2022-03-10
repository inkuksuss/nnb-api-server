package com.smartFarm.was.web.repository;


import com.smartFarm.was.domain.dto.board.UpdateBoardDetailDto;
import com.smartFarm.was.domain.dto.board.BoardDetailDto;
import com.smartFarm.was.domain.response.board.BoardResponse;
import com.smartFarm.was.domain.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface BoardRepository {

    List<BoardResponse> findAllNotice() throws SQLException;

    List<BoardResponse> findAllFAQ() throws SQLException;

    void add(Board board) throws SQLException;

    Optional<BoardDetailDto> findByIdDetail(long boardId) throws SQLException;

    int deleteByIds(long boardId, long memberId) throws SQLException;

    int updateByUpdateForm(UpdateBoardDetailDto updateBoardDetail) throws SQLException;
}
