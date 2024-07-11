package com.jzo2o.market.config;


import com.jzo2o.redis.properties.RedisSyncProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author Jimmy
 */
@Configuration
public class ThreadPoolConfiguration {
    @Bean("syncThreadPool")
    public ThreadPoolExecutor synchronizeThreadPool(RedisSyncProperties redisSyncProperties) {
        int queueNum = redisSyncProperties.getQueueNum();
        int corePoolSize = 1;
        int maximumPoolSize = queueNum;
        long keepAliveTime = 120;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new SynchronousQueue<>();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,handler);
        return threadPoolExecutor;
    }
}
