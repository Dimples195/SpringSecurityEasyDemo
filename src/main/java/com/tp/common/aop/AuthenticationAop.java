package com.tp.common.aop;

import com.tp.common.R;
import com.tp.common.annotation.RequiresPermissions;
import com.tp.entity.LoginUser;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Log4j
public class AuthenticationAop {
    /**
     * 针对权限注解定义一个Aop切面
     */
    @Pointcut("@annotation(com.tp.common.annotation.RequiresPermissions)")
    public void operationLog() {
    }

    @Around("operationLog()")
    public Object around(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        Object[] args = pjp.getArgs();
        boolean authed;
        if (args != null) {
            String[] requiresPermission = requiresPermissions.value();
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
            authed = loginUser.getPermissions().contains(requiresPermission[0]);
            //未拥有权限
            if (!authed) {
                return new R(401, "未拥有权限");
            }
        }
        //拥有权限
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

}
