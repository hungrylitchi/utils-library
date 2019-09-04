package com.litchi.utils.async.controller;

import com.litchi.utils.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: lizhi
 * @create: 2019/09/04
 **/
@RestController
@RequestMapping("async")
@Slf4j
public class SyncController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("test")
    public String test(@RequestParam(value = "times", defaultValue = "20") int times) {
        long start = System.currentTimeMillis();
        List<Future<String>> list = new ArrayList<Future<String>>(times);
        for (int i = 0; i < times; i++) {
            //asyncService.asyncMethod(i);
            list.add(asyncService.asyncMethodHasReturn(i));
        }
        List<String> stringList = new ArrayList<String>(times);
        for (Future<String> stringFuture : list) {
            while (!stringFuture.isDone()) {
            }
            try {
                stringList.add(stringFuture.get(1, TimeUnit.DAYS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }
        return stringList.toString() + " . 耗时:" + (System.currentTimeMillis() - start) + "毫秒";
    }

}
