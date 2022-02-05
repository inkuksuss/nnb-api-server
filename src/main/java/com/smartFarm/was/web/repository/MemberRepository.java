package com.smartFarm.was.web.repository;

import com.smartFarm.was.domain.model.Member;

public interface MemberRepository {

    void saveMember(Member member);
    Member findByEmail(String memberEmail);
}
