package org.example.jack.logic;

import org.example.jack.redis.PeriodicLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstRunner implements Runnable {
    @Autowired
    PeriodicLocker periodicLocker;
    @Override
    public void run() {
        System.out.println("Started first runner");
        periodicLocker.lockPeriodically();
    }
}
