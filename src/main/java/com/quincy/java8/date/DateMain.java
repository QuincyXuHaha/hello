package com.quincy.java8.date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author xuguangquan
 * @date 2019/10/8 星期二
 */
public class DateMain {

    public static void main(String[] args) {

        // 日期
        LocalDate now = LocalDate.now();
        System.out.println("当前日期：" + now);
        System.out.println("当月月初日期：" + now.withDayOfMonth(1));
        System.out.println("当月月初日期：" + now.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("当月月末日期：" + now.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("明天：" + now.plusDays(1));
        System.out.println("下个月：" + now.plusMonths(1));
        System.out.println("是否为闰年：" + now.isLeapYear());

        // 生日匹配
        MonthDay birthDay = MonthDay.of(now.getMonth(), now.getDayOfMonth());
        MonthDay monthDay = MonthDay.from(now);
        System.out.println("生日匹配：" + birthDay.equals(monthDay));

        // 时间
        LocalTime time = LocalTime.now();
        System.out.println("当前时间：" + time);
        System.out.println("当前时间（精确到秒）：" + time.withNano(0));
        System.out.println("2小时后：" + time.plusHours(2));

        // 时间差
        LocalDate specialDate = LocalDate.of(2019, 2, 2);
        Period period = Period.between(specialDate, now);
        System.out.println("相对天差：" + period.getDays());
        System.out.println("相对月差：" + period.getMonths());
        System.out.println("相对年差：" + period.getYears());
        System.out.println("时间差：" + specialDate.until(now));
        System.out.println("总天差：" + specialDate.until(now, ChronoUnit.DAYS));

        // 时间解析、格式化
        LocalDate parse = LocalDate.parse("20190101", DateTimeFormatter.BASIC_ISO_DATE);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy MM dd");
        System.out.println("日期解析：" + parse);
        System.out.println("日期解析2：" + pattern.format(now));

    }

}
