package cn.wolfcode.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hox
 */
@SpringBootApplication
public class ServerLauncher {

    private static final ReentrantLock LOCK   = new ReentrantLock();
    private static final Condition     STOP   = LOCK.newCondition();
    private static final Logger        logger = LoggerFactory.getLogger(ServerLauncher.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        // 启动容器
        ConfigurableApplicationContext ctx = SpringApplication.run(ServerLauncher.class, args);
        // 添加停止容器钩子
        addJVMShutdownHook(ctx);
        try {
            LOCK.lock();
            // 阻塞主线程
            STOP.await();
        } catch (InterruptedException e) {
            logger.warn("Dubbo service server stopped, interrupted by other thread!", e);
        } finally {
            LOCK.unlock();
        }
    }

    private static void addJVMShutdownHook(ConfigurableApplicationContext ctx) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ctx.stop();
                logger.info("Service " + ServerLauncher.class.getSimpleName() + " stopped!");
            } catch (Exception e) {
                logger.error("springboot continer shutdown exception.", e);
            }

            try {
                LOCK.lock();
                STOP.signal();
            } finally {
                LOCK.unlock();
            }
        }, "TestServer-Thread-Shutdown-Hook"));
    }
}
