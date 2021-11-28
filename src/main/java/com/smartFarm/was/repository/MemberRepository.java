package com.smartFarm.was.repository;

import com.smartFarm.was.domain.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface MemberRepository {

    void save(Member member);

    Member findByEmail(String memberEmail);
}
