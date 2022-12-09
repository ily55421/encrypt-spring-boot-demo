package com.link.encrypt.common.secret;

import com.link.encrypt.common.constants.SecretConstant;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author lin 2022/12/9 22:47
 */
public class SecretFilter implements Filter {
    public static ThreadLocal<Boolean> secretThreadLocal = new ThreadLocal<>();
    @Value("${secret.privateKey.h5}")
    private String h5Key;

    @Value("${secret.privateKey.default}")
    private String defaultKey;

    public static ThreadLocal<String> clientPrivateKeyThreadLocal = new ThreadLocal<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 免加密
        if (!request.getRequestURI().startsWith(SecretConstant.PREFIX)) {
            secretThreadLocal.set(Boolean.FALSE);
            filterChain.doFilter(request, response);
        } else {
            // 加密，先只考虑POST情况
            secretThreadLocal.set(Boolean.TRUE);
            // 简单获取对应加密的私钥 Objects.requireNonNull()
            String privateKey = ("H5".equalsIgnoreCase(request.getHeader("clientType"))) ? h5Key : defaultKey;
            clientPrivateKeyThreadLocal.set(privateKey);
            filterChain.doFilter(request, response);
        }
    }
}