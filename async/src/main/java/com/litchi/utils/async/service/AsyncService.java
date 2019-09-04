package com.litchi.utils.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @description:
 * 注解既可以在类上声明表示整个类的方法都是异步的  也可以在方法上声明,表示这个方法是异步的
 * '@Async'所修饰的函数不要定义为static类型，这样异步调用不会生效
 * 异步方法不能与被调用的方法在同一个类中，否则无效
 * 异步类没有使用@Component注解（或其他注解）导致spring无法扫描到异步类 不生效
 * 类中需要使用@Autowired或@Resource等注解自动注入，不能自己手动new对象
 * 如果使用SpringBoot框架必须在启动类中增加@EnableAsync注解
 * 需要返回值的异步函数使用Future来接收
 * @author: lizhi
 * @create: 2019/09/04
 **/
@Component
@Slf4j
@Async("taskExecutor")
public class AsyncService {

    //@ASync
    public void asyncMethod(int index) {
        log.info("asyncMethod start index : {}", index);
        long start = System.currentTimeMillis();
        Random random = new Random();
        int a = random.nextInt(100000);
        int b = random.nextInt(100000);
        while ((a + b) % 199001 != 0) {
            a = random.nextInt(100000);
            b = random.nextInt(100000);
        }
        log.info("asyncMethod end index : {} , a = {} , b= {},a + b = {}, 用时{}毫秒", index, a, b, a + b, System.currentTimeMillis() - start);
    }

    @Async("taskMethodExecutor")
    public Future<String> asyncMethodHasReturn(int index) {
        log.info("asyncMethod start index : {}", index);
        long start = System.currentTimeMillis();
        Random random = new Random();
        int a = random.nextInt(100000);
        int b = random.nextInt(100000);
        while ((a + b) % 199000 != 0) {
            a = random.nextInt(100000);
            b = random.nextInt(100000);
        }
        long duration = System.currentTimeMillis() - start;
        log.info("asyncMethodHasReturn end index : {} , a = {} , b= {},a + b = {}, 用时{}毫秒", index, a, b, a + b, duration);
        return new AsyncResult<String>("index= " + index + " , a = " + a + " , b = " + b + " , 用时:" + duration+"毫秒");
    }
}
