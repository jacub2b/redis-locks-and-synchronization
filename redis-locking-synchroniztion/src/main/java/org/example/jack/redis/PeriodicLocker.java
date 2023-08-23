package org.example.jack.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Service
public class PeriodicLocker {
    @Autowired
    private RedissonClient redisson;

    public void lockPeriodically() {
        while(true) {
            RLock lock = redisson.getLock("jack");
            boolean hasLocked = false;

            try {
                hasLocked = lock.tryLock(5, 15, TimeUnit.SECONDS);
                System.out.println(Instant.now() + ": locked by " + Thread.currentThread() + " : " + hasLocked);
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally{
                lock.unlock();
                System.out.println(Instant.now() + ": unlocked by " + Thread.currentThread() + " : " + hasLocked);
            }
        }
    }

    private static void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Got interrupted exception " + e.getMessage());
        }
    }
}
