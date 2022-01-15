package com.smartFarm.was.web.repository;


import com.smartFarm.was.domain.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BoardRepository {

//    BoardDto getAll();
    void add(Board board);
}
