package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.dto.farm.FarmDto;
import com.smartFarm.was.domain.request.farm.AddFarmForm;
import com.smartFarm.was.domain.response.ResultResponse;

import java.sql.SQLException;
import java.util.List;

public interface FarmService {

    ResultResponse<List<FarmDto>> getFarmList(FarmDto farmDto) throws SQLException;

    ResultResponse<FarmDto> getFarmById(FarmDto farmDto) throws Exception;

    ResultResponse<Long> addFarm(AddFarmForm addFarmForm) throws SQLException;
    ResultResponse<Long> updateFarm(FarmDto farmDto) throws SQLException;

    ResultResponse<Void> deleteFarm(Long farmId) throws SQLException;
}
