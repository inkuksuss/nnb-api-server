package com.smartFarm.was.domain.repository;


import com.smartFarm.was.domain.model.Token;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenRepository {

    void save(Token token);
}
