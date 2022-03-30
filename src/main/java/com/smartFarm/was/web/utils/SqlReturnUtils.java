package com.smartFarm.was.web.utils;

import com.smartFarm.was.domain.entity.Member;
import com.smartFarm.was.domain.entity.sub.Authority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class SqlReturnUtils {

    private SqlReturnUtils() {}

    public static boolean changeSuccess(int returnValue) {
        if (returnValue == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean changeFail(int returnValue) {
        if (returnValue != 1) {
            return true;
        } else {
            return false;
        }
    }
}
