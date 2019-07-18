package com.jango.demo.test.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @author xiongfeixiang
 * @title DataWatcher
 * @date 2019/1/31 上午10:12
 * @since v1.0.0
 */

public class DataWatcher implements Watcher, Runnable {
    private static String hostPort = "192.168.31.249:2181";
    private static String rootDataPath = "/";
    byte zoo_data[] = null;
    ZooKeeper zk;

    public DataWatcher() {
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
            if (zk != null) {
                try {
                    List<String> nodeStrs = zk.getChildren(rootDataPath, this);
                    for (String nodeStr : nodeStrs) {
                        System.out.println(nodeStr);
                        //必须有这句，这句的第二个参数watcher，相当于是注册一个watcher实例，才能实现对对应node监听
                        // 第二个参数传入true 表示使用默认的watcher，否则需要自定义watcher传入
                        //todo 如果要实现对所有node的监听，需递归获取所有的node并 注册监听实例
                        zk.getData("/" + nodeStr, true, null);
                    }
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData(String path) throws InterruptedException, KeeperException {
        zoo_data = zk.getData(path, this, null);
        String zString = new String(zoo_data);
        // The following code prints the current content of the znode to the console:
        System.out.printf("\nCurrent Data @ ZK Path %s: %s", path, zString);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf("\nEvent Received: %s", event.toString());
        //We will process only events of type NodeDataChanged
        if (event.getType() == Event.EventType.NodeDataChanged) {
            try {
                printData(event.getPath());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        DataWatcher dataWatcher = new DataWatcher();
//        dataWatcher.printData(rootDataPath);
        dataWatcher.printData("/xfx");
//        dataWatcher.printData("/le");
        dataWatcher.run();
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
}
