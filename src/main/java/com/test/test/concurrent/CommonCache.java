package com.test.test.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 读写锁，锁不支持升级,仅支持降级
 *
 * @author Snowson
 * @date 2020/10/10 17:46
 */
@Slf4j
public class CommonCache {
    final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    final Lock writeLock = lock.writeLock();
    final Lock readLock = lock.readLock();
    private List<String> cache = new ArrayList<>();

    /**
     * Gets dst.
     * 使用非阻塞方式会造成活锁;正确实现见 {@link CommonCache#getDst2(int)}
     *
     * @param index the index
     * @return dst
     * @throws InterruptedException the interrupted exception
     */
    public String getDst(int index) throws InterruptedException {
        String result = null;
        boolean willGo = true;
        while (willGo) {
            if (readLock.tryLock(5, NANOSECONDS)) {
                log.info("1. read: lock");
                try {
                    if (cache.size() <= index) {
                        readLock.unlock();
                        log.info("2. read: unlock");
                        if (writeLock.tryLock(5, NANOSECONDS)) {
                            try {
                                log.info("3. write: lock");
                                loadFromDB(cache);
                                if (readLock.tryLock(5, NANOSECONDS)) {
                                    log.info("4. read: lock");
                                    result = cache.get(index);
                                    willGo = false;
                                }
                            } finally {
                                writeLock.unlock();
                                log.info("5. write: unlock");
                            }
                        }
                    } else {
                        result = cache.get(index);
                        willGo = false;
                    }
                } finally {
                    readLock.unlock();
                    log.info("6. read: unlock");
                }
            }
        }
        log.info("result: {}", result);
        return result;
    }


    /**
     * Gets dst 2.
     * 缓存懒加载
     * 读写锁不允许升级，允许降级
     *
     * @param index the index
     * @return the dst 2
     */
    public String getDst2(int index) {
        String result;
        readLock.lock();
        log.info("1 read: lock");
        try {
            if (cache.size() <= index) {
                readLock.unlock();
                log.info("2 read: unlock");
                writeLock.lock();
                try {
                    log.info("3 write: lock");
                    if (cache.size() <= index) {
                        log.info("load from db");
                        loadFromDB(cache);
                    }
                    readLock.lock();
                    result = cache.get(index);
                    log.info("result: {}", result);
                } finally {
                    writeLock.unlock();
                    log.info("4. write: unlock");
                }
            } else {
                result = cache.get(index);
                log.info("result: {}", result);
            }
        } finally {
            readLock.unlock();
            log.info("5. read: unlock");
        }
        return result;
    }

    private void loadFromDB(List<String> cache) {
        cache.addAll(Arrays.asList("a", "b", "c", "d", "e"));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        final ExecutorService service = Executors.newFixedThreadPool(5);
//        final CommonCache cache = new CommonCache();
//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            service.submit(() -> cache.getDst2(finalI));
//        }
//        service.shutdown();
        log.info("{}", new CommonCache().getValue());
    }

    public Integer getValue() {
        final AtomicReference<Integer> m = new AtomicReference<>(20);
        m.getAndUpdate((d) -> d + 20);
        return m.get();
    }
}
