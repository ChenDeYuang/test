package tool;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

public class ThreadTool {

    public static ThreadPoolExecutor get(String threadName) {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(10,
                        10,
                        60L,
                        TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(),
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
