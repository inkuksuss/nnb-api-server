package com.smartFarm.was.web.controller;


import com.smartFarm.was.domain.dto.farm.FarmDto;
import com.smartFarm.was.domain.request.farm.AddFarmForm;
import com.smartFarm.was.domain.request.farm.UpdateFarmForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.service.FarmService;
import com.smartFarm.was.web.utils.FormValidationUtils;
import com.smartFarm.was.web.utils.MemberAuthenticationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public ResultResponse<List<FarmDto>> getMyFarmList() {
        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }

        FarmDto farmDto = new FarmDto();
        farmDto.setFmMemberId(getMemberIdByMemberAuthentication());

        return farmService.getFarmList(farmDto);
    }

    @GetMapping("/getFarmList")
    public ResultResponse<List<FarmDto>> getFarmList() {
        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }

        if (isMember()) {
            long memberIdByMemberAuthentication = getMemberIdByMemberAuthentication();
        }
    }

    @PostMapping("/add")
    public ResultResponse<Long> addFarm(@RequestBody AddFarmForm form) {
        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }

        FarmDto addFarmDto = createAddFarmDto(form);

        return farmService.addFarm(addFarmDto);
    }

    private FarmDto createAddFarmDto(AddFarmForm form) {
        FarmDto farmDto = new FarmDto();
        farmDto.setFFarmAddress(form.getFarmAddress());
        farmDto.setFFarmKind(form.getFarmKind());
        farmDto.setFFarmName(form.getFarmName());
        farmDto.setFFarmType(form.getFarmType());
        return farmDto;
    }

    @PostMapping("/update/{farmId}")
    public ResultResponse<Long> updateFarm(@PathVariable Long farmId, @RequestBody UpdateFarmForm form) {
        if (isAnonymous()) {
            return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
        }

        FarmDto formDto = createUpdateFarmDto(form, farmId);

        return farmService.updateFarm(formDto);
    }

    private FarmDto createUpdateFarmDto(UpdateFarmForm form, Long farmId) {
        FarmDto farmDto = new FarmDto();
        farmDto.setFFarmAddress(form.getFarmAddress());
        farmDto.setFFarmKind(form.getFarmKind());
        farmDto.setFFarmName(form.getFarmName());
        farmDto.setFFarmType(form.getFarmType());
        farmDto.setFFarmId(farmId);
        return farmDto;
    }

    @GetMapping("/delete/{farmId}")
    public ResultResponse<Void> deleteForm(@PathVariable Long farmId) {
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
