package com.daqinzhonggong.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daqinzhonggong.domain.AlipayConfig;
import com.daqinzhonggong.domain.vo.TradeVo;
import com.daqinzhonggong.exception.BadRequestException;
import com.daqinzhonggong.mapper.AliPayConfigMapper;
import com.daqinzhonggong.service.AliPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "aliPay")
public class AliPayServiceImpl extends ServiceImpl<AliPayConfigMapper, AlipayConfig> implements AliPayService {

    @Override
    @Cacheable(key = "'config'")
    public AlipayConfig find() {
        AlipayConfig alipayConfig = getById(1L);
        return alipayConfig == null ? new AlipayConfig() : alipayConfig;
    }

    @Override
    @CachePut(key = "'config'")
    @Transactional(rollbackFor = Exception.class)
    public AlipayConfig config(AlipayConfig alipayConfig) {
        alipayConfig.setId(1L);
        saveOrUpdate(alipayConfig);
        return alipayConfig;
    }

    @Override
    public String toPayAsPc(AlipayConfig alipay, TradeVo trade) throws Exception {

        if (alipay.getId() == null) {
            throw new BadRequestException("请先添加相应配置，再操作");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppId(), alipay.getPrivateKey(), alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());

        // 创建API对应的request(电脑网页版)
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        // 订单完成后返回的页面和异步通知地址
        request.setReturnUrl(alipay.getReturnUrl());
        request.setNotifyUrl(alipay.getNotifyUrl());
        // 填充订单参数
        request.setBizContent("{" +
                "    \"out_trade_no\":\"" + trade.getOutTradeNo() + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + trade.getTotalAmount() + "," +
                "    \"subject\":\"" + trade.getSubject() + "\"," +
                "    \"body\":\"" + trade.getBody() + "\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"" + alipay.getSysServiceProviderId() + "\"" +
                "    }" +
                "  }");// 填充业务参数
        // 调用SDK生成表单, 通过GET方式，口可以获取url
        return alipayClient.pageExecute(request, "GET").getBody();

    }

    @Override
    public String toPayAsWeb(AlipayConfig alipay, TradeVo trade) throws Exception {
        if (alipay.getId() == null) {
            throw new BadRequestException("请先添加相应配置，再操作");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(alipay.getGatewayUrl(), alipay.getAppId(), alipay.getPrivateKey(), alipay.getFormat(), alipay.getCharset(), alipay.getPublicKey(), alipay.getSignType());

        double money = Double.parseDouble(trade.getTotalAmount());
        double maxMoney = 5000;
        if (money <= 0 || money >= maxMoney) {
            throw new BadRequestException("测试金额过大");
        }
        // 创建API对应的request(手机网页版)
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setReturnUrl(alipay.getReturnUrl());
        request.setNotifyUrl(alipay.getNotifyUrl());
        request.setBizContent("{" +
                "    \"out_trade_no\":\"" + trade.getOutTradeNo() + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + trade.getTotalAmount() + "," +
                "    \"subject\":\"" + trade.getSubject() + "\"," +
                "    \"body\":\"" + trade.getBody() + "\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"" + alipay.getSysServiceProviderId() + "\"" +
                "    }" +
                "  }");
        return alipayClient.pageExecute(request, "GET").getBody();
    }

}
