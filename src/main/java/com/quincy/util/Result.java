package com.quincy.util;

import lombok.Data;

/**
 * @author quincy
 * @date 2019/3/15 星期五
 */
@Data
public class Result<R> {

    private R data;


    private Result(R r) {
        data = r;
    }

    public static <R> Result<R> suc(R r) {
        return new Result<>(r);
    }


    public static void main(String[] args) {
        Result<String> r = new Result<>("qqqq");
        System.out.println(r);
    }


}
