package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.dto.comment.AddCommentDto;
import com.smartFarm.was.domain.dto.comment.GetCommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Mapper
@Repository
public interface CommentRepository {

    int addComment(AddCommentDto addCommentDto) throws SQLException;

    GetCommentDto getComment(GetCommentDto getCommentDto) throws SQLException;

    int updateComment(UpdateComm comment) throws SQLException;

    int deleteComment(AddCommentDto comment) throws SQLException;
}

