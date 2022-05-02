package com.smartFarm.was.web.controller;


import com.smartFarm.was.domain.dto.farm.FarmDto;
import com.smartFarm.was.domain.request.farm.AddFarmForm;
import com.smartFarm.was.domain.request.farm.GetFarmForm;
import com.smartFarm.was.domain.request.farm.UpdateFarmForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.service.FarmService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static com.smartFarm.was.web.utils.FormValidationUtils.*;
import static com.smartFarm.was.web.utils.MemberAuthenticationUtils.*;

@RestController
@RequestMapping("/farm")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping("/me")
    public ResultResponse<List<FarmDto>> getMyFarmList() throws Exception{
        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }

        FarmDto farmDto = new FarmDto();
        farmDto.setFmMemberId(getMemberIdByMemberAuthentication());

        return farmService.getFarmList(farmDto);
    }

    @GetMapping("/getFarmList")
    public ResultResponse<List<FarmDto>> getFarmList(GetFarmForm form) throws Exception {

        FarmDto farmDto = createFarmDto(form);

        return farmService.getFarmList(farmDto);
    }

    private FarmDto createFarmDto(GetFarmForm form) {
        FarmDto farmDto = new FarmDto();
        if (!illegalString(form.getFarmAddress())) {
            farmDto.setFarmAddress(form.getFarmAddress());
        }
        if (!illegalString(form.getFarmType())) {
            farmDto.setFarmType(form.getFarmType());
        }
        if (!illegalString(form.getFarmKind())) {
            farmDto.setFarmKind(form.getFarmKind());
        }
        if (!illegalString(form.getFarmName())) {
            farmDto.setFarmName(form.getFarmName());
        }
        if (!illegalString(form.getFmAuthority())) {
            farmDto.setFmAuthority(form.getFmAuthority());
        }
        if (!illegalString(form.getFmStatus())) {
            farmDto.setFmStatus(form.getFmStatus());
        }
        return farmDto;
    }

    @PostMapping("/add")
    public ResultResponse<Long> addFarm(@RequestBody AddFarmForm form) throws Exception {
        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }

        return farmService.addFarm(form);
    }

    @PostMapping("/update/{farmId}")
    public ResultResponse<Long> updateFarm(@PathVariable Long farmId, @RequestBody UpdateFarmForm form) throws SQLException {
        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }

        FarmDto farmDto = createUpdateFarmDto(form, farmId);

        return farmService.updateFarm(farmDto);
    }

    private FarmDto createUpdateFarmDto(UpdateFarmForm form, Long farmId) {
        FarmDto farmDto = new FarmDto();
        farmDto.setFarmAddress(form.getFarmAddress());
        farmDto.setFarmKind(form.getFarmKind());
        farmDto.setFarmName(form.getFarmName());
        farmDto.setFarmType(form.getFarmType());
        farmDto.setFarmId(farmId);
        farmDto.setFmStatus(form.getFmFarmStatus());
        return farmDto;
    }

    @GetMapping("/delete/{farmId}")
    public ResultResponse<Void> deleteForm(@PathVariable Long farmId) throws Exception {
        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }

        if (!illegalLongValue(farmId)) {
            return farmService.deleteFarm(farmId);
        } else {
            return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.INVALID_PARAMETER.getCode(), ResultCode.INVALID_PARAMETER.getMessage());
        }
    }
}
