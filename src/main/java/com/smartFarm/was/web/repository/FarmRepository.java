package com.smartFarm.was.web.repository;


import com.smartFarm.was.domain.dto.farm.FarmDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface FarmRepository {

    List<FarmDto> getFarmList(FarmDto farmDto) throws SQLException;

    Optional<FarmDto> getFarmById(FarmDto farmDto) throws SQLException;

    int addFarmMapping(FarmDto farmDto) throws SQLException;

    int addFarm(FarmDto farmDto) throws SQLException;

    int updateFarmMapping(FarmDto farmDto) throws SQLException;

    int updateFarm(FarmDto farmDto) throws SQLException;

    int deleteFarmMapping(FarmDto farmDto) throws SQLException;

    int deleteFarm(FarmDto farmDto) throws SQLException;
}
