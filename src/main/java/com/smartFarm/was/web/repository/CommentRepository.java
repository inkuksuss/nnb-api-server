package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Mapper
@Repository
public interface CommentRepository {

    void add(AddCommentDto comment) throws SQLException;
}

