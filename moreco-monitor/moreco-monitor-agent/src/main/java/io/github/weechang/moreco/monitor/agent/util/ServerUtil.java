package io.github.weechang.moreco.monitor.agent.util;

import io.github.weechang.mcm.core.server.*;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Server 数据搜集工具类
 *
 * @author zhangwei
 * date 2018/12/29
 * time 14:35
 */
@Slf4j
public class ServerUtil {

    public static Server getServer() {
        Server server = new Server();
        HardWare hardWare = getHardWare();
        OS os = getOS();
        server.setHardWare(hardWare);
        server.setOs(os);
        return server;
    }

    public static HardWare getHardWare() {
        HardWare hardWare = new HardWare();
        Cpu cpu = getCpu();
        Memory memory = getMemory();
        List<Disk> disks = getDisks();
        List<NetWork> netWorks = getNetWorks();
        hardWare.setCpu(cpu);
        hardWare.setMemory(memory);
        hardWare.setDisks(disks);
        hardWare.setNetWorks(netWorks);
        return hardWare;
    }

    public static OS getOS() {
        OS os = new OS();
        SystemInfo systemInfo = getSystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        os.setHostName(operatingSystem.getNetworkParams().getHostName());
        os.setFamily(operatingSystem.getFamily());
        os.setVersion(operatingSystem.getVersion().getVersion());
        os.setBuildNumber(operatingSystem.getVersion().getBuildNumber());
        os.setBitness(operatingSystem.getBitness());
        os.setProcessCount(operatingSystem.getProcessCount());
        os.setThreadCount(operatingSystem.getThreadCount());
        List<FileStore> fileStores = getFileStores();
        os.setFileStores(fileStores);
        os.setDefaultIp(getDefaultIp());
        return os;
    }

    public static Cpu getCpu() {
        Cpu cpu = new Cpu();
        SystemInfo systemInfo = getSystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        CentralProcessor processor = hardware.getProcessor();
        return cpu;
    }

    public static Memory getMemory() {
        Memory memory = new Memory();
        SystemInfo systemInfo = getSystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        GlobalMemory globalMemory = hardware.getMemory();
        memory.setMemTotal(globalMemory.getTotal());
        memory.setMemAvailable(globalMemory.getAvailable());
        memory.setSwapTotal(globalMemory.getSwapTotal());
        memory.setSwapUsed(globalMemory.getSwapUsed());
        memory.setSwapPagesIn(globalMemory.getSwapPagesIn());
        memory.setSwapPagesOut(globalMemory.getSwapPagesOut());
        memory.setPageSize(globalMemory.getPageSize());
        return memory;
    }

    public static List<Disk> getDisks() {
        List<Disk> disks = new ArrayList<>();
        SystemInfo systemInfo = getSystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        HWDiskStore[] diskStores = hardware.getDiskStores();
        for (HWDiskStore diskStore : diskStores) {
            Disk disk = new Disk();
            disk.setSize(diskStore.getSize());
            disk.setReads(diskStore.getReads());
            disk.setReadBytes(diskStore.getReadBytes());
            disk.setWrites(diskStore.getWrites());
            disk.setWriteBytes(diskStore.getWriteBytes());
            disk.setCurrentQueueLength(diskStore.getCurrentQueueLength());
            disk.setTransferTime(diskStore.getTransferTime());
            disks.add(disk);
        }
        return disks;
    }

    public static List<NetWork> getNetWorks() {
        List<NetWork> netWorks = new ArrayList<>();
        SystemInfo systemInfo = getSystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        NetworkIF[] networkIFs = hardware.getNetworkIFs();
        for (NetworkIF networkIF : networkIFs) {
            NetWork netWork = new NetWork();
            netWork.setMac(networkIF.getMacaddr());
            netWork.setIpv4(networkIF.getIPv4addr());
            netWork.setIpv6(networkIF.getIPv6addr());
            netWork.setBytesRecv(networkIF.getBytesRecv());
            netWork.setBytesSent(networkIF.getBytesSent());
            netWork.setPacketsRecv(networkIF.getPacketsRecv());
            netWork.setPacketsSent(networkIF.getPacketsSent());
            netWork.setSpeed(networkIF.getSpeed());
            netWorks.add(netWork);
        }
        return netWorks;
    }

    public static List<FileStore> getFileStores() {
        List<FileStore> fileStores = new ArrayList<>();
        SystemInfo systemInfo = getSystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem fileSystem = operatingSystem.getFileSystem();
        OSFileStore[] osFileStores = fileSystem.getFileStores();
        for (OSFileStore osFileStore : osFileStores) {
            FileStore fileStore = new FileStore();
            fileStore.setName(osFileStore.getName());
            fileStore.setMount(osFileStore.getMount());
            fileStore.setFsType(osFileStore.getType());
            fileStore.setTotalSpace(osFileStore.getUsableSpace());
            fileStore.setUsableSpace(osFileStore.getUsableSpace());
            fileStores.add(fileStore);
        }
        return fileStores;
    }

    /**
     * 获取默认ip
     *
     * @return 默认ip
     */
    public static String getDefaultIp() {
        String defaultIp = "127.0.0.1";
        try {
            InetAddress ip = null;
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !ip.getHostAddress().equals("127.0.0.1")) {
                        String[] ipArray = ip.toString().split("/");
                        defaultIp = ipArray[1];
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            log.error("get default ip error", e);
        }
        return defaultIp;
    }

    private static SystemInfo getSystemInfo() {
        return new SystemInfo();
    }
}
