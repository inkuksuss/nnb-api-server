package com.smartFarm.was.web.service.impl;

import com.smartFarm.was.domain.dto.farm.FarmDto;
import com.smartFarm.was.domain.request.farm.AddFarmForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.repository.FarmRepository;
import com.smartFarm.was.web.service.FarmService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

import static com.smartFarm.was.web.utils.MemberAuthenticationUtils.*;
import static com.smartFarm.was.web.utils.StatusCheckUtils.*;

@Service
public class FarmServiceImpl implements FarmService {


    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public ResultResponse<List<FarmDto>> getFarmList(FarmDto farmDto) throws SQLException {
        List<FarmDto> farmList = farmRepository.getFarmList(farmDto);

        return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), farmList);
    }

    @Override
    public ResultResponse<FarmDto> getFarmById(FarmDto farmDto) throws Exception {
        FarmDto farm = farmRepository.getFarmById(farmDto).orElseThrow(() -> new NoSuchElementException("존재하지 않는 농장입니다."));

        return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), farm);
    }

    @Override
    public ResultResponse<Long> addFarm(AddFarmForm addFarmForm) throws SQLException {

        FarmDto farmDto = FarmDto.addFarmDto(addFarmForm);

        int farmResult = farmRepository.addFarm(farmDto);

        if (querySuccess(farmResult)) {
            farmDto.setFmStatus(addFarmForm.getFmStatus());
            farmDto.setFmAuthority(addFarmForm.getFmAuthority());
            farmDto.setFmMemberId(getMemberIdByMemberAuthentication());

            return this.addFarmMapping(farmDto);
        } else {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    private ResultResponse<Long> addFarmMapping(FarmDto farmDto) throws SQLException{

        createAddMappingDto(farmDto);

        int result = farmRepository.addFarmMapping(farmDto);

        if (querySuccess(result)) {
            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), farmDto.getFarmId());
        } else {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    private void createAddMappingDto(FarmDto farmDto) {
        farmDto.setFmFarmId(farmDto.getFmFarmId());
        farmDto.setFmFarmId(farmDto.getFarmId());
        farmDto.setFmCreated(new Timestamp(System.currentTimeMillis()));
        farmDto.setFmStateDel('N');
    }

    @Override
    public ResultResponse<Long> updateFarm(FarmDto farmDto) throws SQLException {
        int result = farmRepository.updateFarm(farmDto);

        if (querySuccess(result)) {
            return this.updateFarmMapping(farmDto);
        } else {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    private ResultResponse<Long> updateFarmMapping(FarmDto farmDto) throws SQLException {

        int result = farmRepository.updateFarmMapping(farmDto);

        if (querySuccess(result)) {
            return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), farmDto.getFarmId());
        } else {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
        }
    }

    @Override
    public ResultResponse<Void> deleteFarm(Long farmId) throws SQLException {
        ResultResponse<Void> response = this.deleteFarmMapping(farmId);

        FarmDto farmDto = new FarmDto();
        farmDto.setFarmId(farmId);
        farmDto.setFarmStateDel('Y');

        if (response.getResultCode() == 0) {
            int result = farmRepository.deleteFarm(farmDto);

            if (!querySuccess(result)) {
                response.setHttpStatus(HttpStatus.BAD_REQUEST);
                response.setResultCode(ResultCode.FAIL.getCode());
                response.setResultMessage(ResultCode.FAIL.getMessage());
            }
        }
        return response;
    }

    private ResultResponse<Void> deleteFarmMapping(Long farmId) throws SQLException {
        FarmDto farmDto = new FarmDto();
        farmDto.setFmFarmId(farmId);
        farmDto.setFmMemberId(getMemberIdByMemberAuthentication());
        farmDto.setFmStateDel('Y');

        int result = farmRepository.deleteFarmMapping(farmDto);
        ResultResponse<Void> response = new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());

        if (querySuccess(result)) {
            response.setHttpStatus(HttpStatus.OK);
            response.setResultCode(ResultCode.SUCCESS.getCode());
            response.setResultMessage(ResultCode.SUCCESS.getMessage());
        }

        return response;
    }
}
