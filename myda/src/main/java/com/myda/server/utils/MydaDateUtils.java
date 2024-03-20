package com.myda.server.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author ccQi
 * @Package com.myda.server.utils
 * @Description: TODO
 * @date
 */
public class MydaDateUtils {

    // 计算怀孕的周数
    public static int calculatePregnancyWeeks(Date dueDate) {
        LocalDate dueLocalDate = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // 减去280天
        LocalDate estimatedConceptionDate = dueLocalDate.minusDays(280);
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 计算怀孕周数
        Period period = Period.between(currentDate, estimatedConceptionDate);
        int pregnancyWeeks = (period.getYears() * 52) + (period.getMonths() * 4) + (period.getDays() / 7);
        // 返回怀孕周数
        return Math.abs(pregnancyWeeks);
    }

    public static long daysBetween(Date startDate, Date endDate) {
        long diffInMillies = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
