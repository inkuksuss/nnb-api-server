package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository {

    void saveMember(Member member);
    Member findByEmail(String memberEmail);
    void saveToken(String token, String memberEmail);
}
