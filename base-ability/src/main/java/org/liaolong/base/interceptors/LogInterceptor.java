package org.liaolong.base.interceptors;

import org.apache.commons.lang3.StringUtils;
import org.liaolong.base.constant.Constants;
import org.liaolong.base.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截处理
 *
 * @author ll
 * @since 2025-03-02 9:31
 */
@Component
public class LogInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = StringUtils.defaultIfBlank(request.getHeader(Constants.TRACE_ID_HEADER), UUIDUtils.getUUID());
        MDC.put(Constants.TRACE_ID, traceId);
        return true;
    }
}
