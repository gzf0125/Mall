package com.pingan.quartz;

import com.pingan.service.SaleListService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * 超过14天的订单自动确认收货
 */
@Configuration
@EnableScheduling
public class AutomaticReceiptConfirmation {

    @Resource
    private SaleListService saleListService;

    /**
     * 超过14天的订单自动确认收货
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void work() {
        saleListService.automaticReceiptConfirmation();
    }
}
