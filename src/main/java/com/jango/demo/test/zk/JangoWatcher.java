package com.jango.demo.test.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author xiongfeixiang
 * @title MainTest
 * @date 2019/1/30 下午4:44
 * @since v1.0.0
 */
public class JangoWatcher implements Watcher {

    private static JangoWatcher instance = null;

    public static JangoWatcher getInstance() {
        if (instance == null) {
            instance = new JangoWatcher();
        }
        return instance;
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {  //zk连接成功通知事件
            if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                try {
                    System.out.println("配置已修改，新值为：" + new String(JangoZkClient.getZkClientInstance().getData(watchedEvent.getPath(), true, null)));
                } catch (Exception e) {
                }
            }
        }

    }
}
