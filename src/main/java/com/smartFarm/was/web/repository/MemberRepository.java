package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.entity.Member;

import java.sql.SQLException;
import java.util.Optional;

public interface MemberRepository {

    int addMember(Member member) throws SQLException;

    Optional<Member> findByEmail(String memberEmail);
}
