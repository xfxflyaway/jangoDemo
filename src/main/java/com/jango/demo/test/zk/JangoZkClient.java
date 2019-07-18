package com.jango.demo.test.zk;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiongfeixiang
 * @title JangoZkClient
 * @date 2019/1/30 下午5:56
 * @since v1.0.0
 */
public class JangoZkClient {
    private static String zkHost = "localhost:2181";

    private static int sessionTimeout = 5000;

    private static ZooKeeper zk;
    public static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private JangoZkClient() {
    }

    public static ZooKeeper getZkClientInstance() {
        try {
            if (zk == null) {
                zk = new ZooKeeper(zkHost, sessionTimeout, JangoWatcher.getInstance());
            }
            return zk;
        } catch (Exception e) {

        }
        return null;
    }
    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws Exception {
        JangoZkClient c = new JangoZkClient();
        ZooKeeper zk = JangoZkClient.getZkClientInstance();

        zk.getChildren("/",false).forEach(s->System.out.println(s));
//        c.run();
//        connectedSemaphore.wait();
        // get
        System.out.println(new String(zk.getData("/xfx", false, null)));
//        Thread.currentThread().sleep(Integer.MAX_VALUE);
        // create
        String createResult = zk.create("/xfx1","xfx1data1".getBytes(),null,null);
        System.out.println(createResult);
    }
}
