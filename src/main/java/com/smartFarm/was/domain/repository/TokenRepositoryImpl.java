package com.smartFarm.was.domain.repository;

import com.smartFarm.was.domain.model.Token;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    SqlSession sqlSession;

    @Override
    public void save(Token token) {
        sqlSession.insert("save");
    }
}
