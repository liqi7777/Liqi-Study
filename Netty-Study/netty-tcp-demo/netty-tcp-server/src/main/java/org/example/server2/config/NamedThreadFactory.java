package org.example.server2.config;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

    public static AtomicInteger counter = new AtomicInteger(1);

    private String name = this.getClass().getName();

    private boolean deamon;//守护线程

    private int priority; //线程优先级

    public NamedThreadFactory(String name) {
        this(name, false);
    }


    public NamedThreadFactory(String name, boolean deamon) {
        this(name, deamon, -1);
    }

    public NamedThreadFactory(String name, boolean deamon, int priority) {
        this.name = name;
        this.deamon = deamon;
        this.priority = priority;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "[" + counter.getAndIncrement() + "]");
        thread.setDaemon(deamon);
        if (priority != -1) {
            thread.setPriority(priority);
        }
        return thread;
    }

}
