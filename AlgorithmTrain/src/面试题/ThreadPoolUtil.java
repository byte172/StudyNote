package 面试题;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

    public ThreadPoolUtil() {}

    private static ThreadPoolExecutor threadPoolUtil;

    public static ThreadPoolExecutor getThreadPool () {
        if(threadPoolUtil == null){
            threadPoolUtil = new ThreadPoolExecutor(300,
                    400,
                    30,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(300));
        }
        return threadPoolUtil;
    }

    public static Future submitTask (Runnable runnable) {
        if (threadPoolUtil == null) {
            synchronized (ThreadPoolUtil.class) {
                if (threadPoolUtil == null) {
                    threadPoolUtil = new ThreadPoolExecutor(300,
                            400,
                            30,
                            TimeUnit.SECONDS,
                            new ArrayBlockingQueue<Runnable>(300));
                }
            }
        }
        while (threadPoolUtil.getActiveCount() >= 500) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Future future = threadPoolUtil.submit(runnable);
        return future;
    }
}
