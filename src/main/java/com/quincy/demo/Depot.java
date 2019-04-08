package com.quincy.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 仓库
 *
 * @author quincy
 * @date 2018/2/8 星期四
 */
public class Depot {

    private static final Logger LOGGER = LoggerFactory.getLogger(Depot.class);

    private ConcurrentHashMap<Long, Good> goods = new ConcurrentHashMap<>(1024);

    /**
     * 生产
     *
     * @param good 需要生产的物品
     */
    public void product(Good good) {
        if (goods.containsKey(good.getNum())) {
            return;
        }
        goods.put(good.getNum(), good);
        LOGGER.info("\n--------num={}----name={} has been product.", good.getNum(), good.getName());
    }

    /**
     * 消费
     */
    public void consume() {
        for (Map.Entry<Long, Good> entry : goods.entrySet()) {
            Good good = entry.getValue();
            if (goods.remove(good.getNum(), good)) {
                LOGGER.info("\n--------num={}----name={} has been consumed.", good.getNum(), good.getName());
            }
        }
    }

}
