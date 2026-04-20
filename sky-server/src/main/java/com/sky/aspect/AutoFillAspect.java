package com.sky.aspect;


import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {}
    /**
     * 前置置通知
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行数据填充");
        //获取到当前方法上的数据库操作类型
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();//获取方法签名
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获取方法上的注解对象
        OperationType operationType = autoFill.value();//获取数据库操作类型
        //获取方法上的参数
        Object[] args = joinPoint.getArgs();//获取方法上的参数,默认实体是第一个
        if (args == null || args.length == 0) {
            return;
        }
        Object object = args[0];//获取参数对象
        //准备 赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        //根据对应的数据库操作类型，为对应的属性赋值
        try {
            if (operationType == OperationType.INSERT) {
                 //为插入操作的属性赋值
                setFieldValue(object, AutoFillConstant.SET_CREATE_TIME, now);
                setFieldValue(object,  AutoFillConstant.SET_UPDATE_TIME, now);
                setFieldValue(object, AutoFillConstant.SET_CREATE_USER, currentId);
                setFieldValue(object, AutoFillConstant.SET_UPDATE_USER, currentId);

            }
            else if (operationType == OperationType.UPDATE) {
                //为更新操作的属性赋值
                setFieldValue(object, AutoFillConstant.SET_UPDATE_TIME, now);
                setFieldValue(object, AutoFillConstant.SET_UPDATE_USER, currentId);

            }
        } catch (Exception e) {
            log.error("自动填充失败：{}", e.getMessage());
            throw new RuntimeException(e);
        }

    }
    /**
     * 反射设置字段值的工具方法（避免代码重复）
     */
    private void setFieldValue(Object object, String fieldName, Object value) {
        try {
            //通过反射获取object的属性值
            Field field = object.getClass().getDeclaredField(fieldName);
            //通过反射设置属性值
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
