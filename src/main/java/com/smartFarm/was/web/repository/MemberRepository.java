package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.entity.Member;

import java.sql.SQLException;

public interface MemberRepository {

    void saveMember(Member member) throws SQLException;

    Member findByEmail(String memberEmail) throws SQLException;
}
