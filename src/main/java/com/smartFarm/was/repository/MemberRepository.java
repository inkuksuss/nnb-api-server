package com.smartFarm.was.repository;

import com.smartFarm.was.dto.JoinDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberRepository {

    void addMember(JoinDTO joinDTO);
}
