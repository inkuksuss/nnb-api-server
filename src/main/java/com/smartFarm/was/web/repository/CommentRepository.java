package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentRepository {

    void add(Comment comment);
}

