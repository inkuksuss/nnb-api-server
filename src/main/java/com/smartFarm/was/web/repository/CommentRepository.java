package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.dto.comment.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface CommentRepository {

    void addComment(AddCommentDto addCommentDto) throws SQLException;

    List<CommentDto> getCommentList(GetCommentDto getCommentDto) throws SQLException;

    void updateComment(UpdateCommentDto updateCommentDto) throws SQLException;

    void deleteComment(DeleteCommentDto deleteCommentDto) throws SQLException;
}

