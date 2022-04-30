package com.smartFarm.was.web.repository;


import com.smartFarm.was.domain.dto.farm.FarmDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface FarmRepository {

    List<FarmDto> getMyFarmList(FarmDto farmDto);

    Optional<FarmDto> getFarmById(FarmDto farmDto);

    int addFarmMapping(FarmDto farmDto);

    int addFarm(FarmDto farmDto);

    int updateFarmMapping(FarmDto farmDto);

    int updateFarm(FarmDto farmDto);

    int deleteFarmMapping(FarmDto farmDto);

    int deleteFarm(FarmDto farmDto);
}
