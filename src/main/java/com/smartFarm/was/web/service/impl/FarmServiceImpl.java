package com.smartFarm.was.web.service.impl;

import com.smartFarm.was.domain.dto.farm.FarmDto;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.service.FarmService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {


    @Override
    public ResultResponse<List<FarmDto>> getFarmList(FarmDto farmDto) {

    }

    @Override
    public ResultResponse<FarmDto> getFarmById(FarmDto farmDto) {

    }

    @Override
    public ResultResponse<Void> addFarmMapping(FarmDto farmDto) {

    }

    @Override
    public ResultResponse<Long> addFarm(FarmDto farmDto) {

    }

    @Override
    public ResultResponse<Void> updateFarmMapping(FarmDto farmDto) {

    }

    @Override
    public ResultResponse<Long> updateFarm(FarmDto farmDto) {

    }

    @Override
    public ResultResponse<Void> deleteFarm(Long farmId) {

    }
}
