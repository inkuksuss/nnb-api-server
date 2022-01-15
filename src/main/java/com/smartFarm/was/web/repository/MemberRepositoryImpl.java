package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.model.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final SqlSession sqlSession;
    private final String mapperLocation;
    private final RedisTemplate redisTemplate;

    public MemberRepositoryImpl(
            SqlSession sqlSession,
            @Value("${mybatis.mapper-locations}") String mapperLocation, RedisTemplate redisTemplate) {
        this.sqlSession = sqlSession;
        this.mapperLocation = mapperLocation;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveMember(Member member) {
        sqlSession.insert(mapperLocation + "MemberRepository.save", member);
    }

    @Override
    public Member findByEmail(String memberEmail) {
        return sqlSession.selectOne(mapperLocation + "MemberRepository.findByEmail", memberEmail);
    }

    @Override
    public void saveToken(String memberEmail, String token) {
        redisTemplate.opsForValue().set(memberEmail, token);
    }
}
