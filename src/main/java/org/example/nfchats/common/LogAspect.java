package org.example.nfchats.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Log;
import org.example.nfchats.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 处理切面的“监控”
 */
@Component
@Aspect
public class LogAspect {

    @Resource
    private LogService logService;

    @Around("@annotation(autoLog)")
    public Object doAround(ProceedingJoinPoint joinPoint, AutoLog autoLog) throws Throwable {

        // 操作内容，我们在注解里已经定义了value()，然后再需要切入的接口上面去写上对应的操作内容即可
        String name = autoLog.value();
        // 操作时间（当前时间）
        String time = DateUtil.now();
        // 操作人
        String username = "";
        Admin user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isNotNull(user)) {
            username = user.getUsername();
            System.out.println(username);
        }
        // 操作人IP
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();

        // 执行具体的接口
        Result result = (Result) joinPoint.proceed();
        if (result.getData()==null){
            result.setData("日志");
        }
        Object data = result.getData();
        System.out.println(data);

        if (data instanceof Admin) {
            Admin admin = (Admin) data;
            username = admin.getUsername();
        }

        // 再去往日志表里写一条日志记录
        Log log = new Log(null, name, time, username, ip);
        logService.add(log);

        // 你可以走了，去返回前台报到吧~
        return result;
    }
}
