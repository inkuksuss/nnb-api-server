package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.entity.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final SqlSession sqlSession;
    private final String mapperLocation;

    public MemberRepositoryImpl(
            SqlSession sqlSession,
            @Value("${mybatis.mapper-locations}") String mapperLocation) {
        this.sqlSession = sqlSession;
        this.mapperLocation = mapperLocation;
    }

    @Override
    public void saveMember(Member member) {
        sqlSession.insert(mapperLocation + "MemberRepository.saveMember", member);
    }

    @Override
    public Member findByEmail(String memberEmail) {
        return sqlSession.selectOne(mapperLocation + "MemberRepository.findByEmail", memberEmail);
    }
}
