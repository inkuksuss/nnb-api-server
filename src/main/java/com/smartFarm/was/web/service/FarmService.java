package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.farm.FarmDto;
import com.smartFarm.was.domain.response.ResultResponse;

import java.util.List;

public interface FarmService {

    ResultResponse<List<FarmDto>> getFarmList(FarmDto farmDto);

    ResultResponse<FarmDto> getFarmById(FarmDto farmDto);

    ResultResponse<Void> addFarmMapping(FarmDto farmDto);

    ResultResponse<Long> addFarm(FarmDto farmDto);

    ResultResponse<Void> updateFarmMapping(FarmDto farmDto);

    ResultResponse<Long> updateFarm(FarmDto farmDto);

    ResultResponse<Void> deleteFarmMapping(FarmDto farmDto);

    ResultResponse<Void> deleteFarm(Long farmId);
}
