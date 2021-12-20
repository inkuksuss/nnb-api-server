package com.smartFarm.was.domain.repository;

import com.smartFarm.was.domain.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface MemberRepository {

    void save(Member member);
    Optional<Member> findByEmail(String memberEmail);
}
