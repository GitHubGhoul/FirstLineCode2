package com.wxd.javacode.netprogram;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class RMIRemote {
    public static void main(String[] args) throws Exception{
        callClient();
    }

    interface WorldClock extends Remote{
        LocalDateTime getLocalDataTime(String zoneId) throws RemoteException;
    }

    static class WorldClockService implements WorldClock{

        @Override
        public LocalDateTime getLocalDataTime(String zoneId) throws RemoteException {
            return LocalDateTime.now(ZoneId.of(zoneId)).withNano(0);
        }
    }

    private static void callService() throws Exception{
        System.out.println("create World clock remote service...");
        // 实例化一个WorldClock:
        WorldClock worldClock = new WorldClockService();
        // 将此服务转换为远程服务接口:
        WorldClock skeleton = (WorldClock) UnicastRemoteObject.exportObject(worldClock, 0);
        // 将RMI服务注册到1099端口:
        Registry registry = LocateRegistry.createRegistry(1099);
        // 注册此服务，服务名为"WorldClock":
        registry.rebind("WorldClock", skeleton);
    }

    private static void callClient() throws Exception{
        // 连接到服务器localhost，端口1099:
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        // 查找名称为"WorldClock"的服务并强制转型为WorldClock接口:
        WorldClock worldClock = (WorldClock) registry.lookup("WorldClock");
        // 正常调用接口方法:
        LocalDateTime now = worldClock.getLocalDataTime("Asia/Shanghai");
        // 打印调用结果:
        System.out.println(now);
    }
}
