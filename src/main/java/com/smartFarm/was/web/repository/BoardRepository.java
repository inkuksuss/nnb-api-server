package com.smartFarm.was.web.repository;


import com.smartFarm.was.domain.dto.board.DeleteBoardDto;
import com.smartFarm.was.domain.dto.board.UpdateBoardDto;
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

    List<BoardResponse> selectAllNotices() throws SQLException;

    List<BoardResponse> selectAllFAQs() throws SQLException;

    void insertBoard(Board board) throws SQLException;

    Optional<BoardDetailDto> selectDetailById(long boardId) throws SQLException;

    int deleteBoardByDeleteBoardDto(DeleteBoardDto deleteBoardDto) throws SQLException;

    int updateBoardByUpdateForm(UpdateBoardDto updateBoardDto) throws SQLException;
}
