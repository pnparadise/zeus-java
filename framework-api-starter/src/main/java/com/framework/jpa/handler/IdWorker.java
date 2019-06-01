package com.framework.jpa.handler;

/**
 * @author zhiqiang.huang
 */
public class IdWorker {
    /**
     * 主机和进程的机器码
     */
    private static final Sequence worker = new Sequence();

    public static long getId() {
        return worker.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(worker.nextId());
    }

}
