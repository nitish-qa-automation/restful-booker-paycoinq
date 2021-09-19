package com.heroukapp.apis.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestUtil {

    public String getFutureDateInFormat(String inputFormat , int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(inputFormat));
    }


}