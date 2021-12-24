package com.user.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ThreadTool {


    @Value("${THREAD.NAME}")
    private String threadName;



    @Bean(value = "threadPoolExecutor")
    public ThreadPoolExecutor get() {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(10,
                        10,
                        60L,
                        TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(),
                       // new CustomizableThreadFactory(threadName));
                        getFactory(threadName));
        return executor;
    }

    public static ExecutorService getSingleExecutor(){
        //单线程的线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();

        return executor;
    }

    public static ThreadFactory getFactory(String name) {
        return new CustomizableThreadFactory(name);
    }


}
