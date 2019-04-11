package com.quincy.netty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author quincy
 * @date 2019/4/11 星期四
 */
@Data
@AllArgsConstructor
public class Session {

    private Long userId;
    private String name;

}
