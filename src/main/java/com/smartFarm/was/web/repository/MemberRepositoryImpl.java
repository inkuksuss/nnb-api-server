package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.entity.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

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
    public void addMember(Member member) {
        sqlSession.insert(mapperLocation + "MemberRepository.addMember", member);
    }

    @Override
    public Optional<Member> findByEmail(String memberEmail) {
        Member member = sqlSession.selectOne(mapperLocation + "MemberRepository.findByEmail", memberEmail);
        return Optional.ofNullable(member);
    }
}
