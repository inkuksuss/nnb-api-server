package com.smartFarm.was.web.exception.custom;

import org.apache.ibatis.javassist.NotFoundException;

public class DataNotFoundException extends NotFoundException {
        public DataNotFoundException(String message) {
            super(message);
        }
}
