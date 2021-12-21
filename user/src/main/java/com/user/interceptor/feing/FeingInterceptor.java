package com.user.interceptor.feing;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeingInterceptor implements RequestInterceptor {
    /**
     * feing拦截器
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("token","8djsdfrenskpafnke0+=-dwefweio");
    }
}
