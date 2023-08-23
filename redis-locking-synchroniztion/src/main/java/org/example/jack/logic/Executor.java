package org.example.jack.logic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Executor implements CommandLineRunner {
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private FirstRunner firstRunner;

    @Autowired
    private SecondRunner secondRunner;

    @Override
    public void run(String... args) throws Exception {
        executeRunnables();
    }
    private void executeRunnables() {
        taskExecutor.execute(firstRunner);
        taskExecutor.execute(secondRunner);
    }
}
