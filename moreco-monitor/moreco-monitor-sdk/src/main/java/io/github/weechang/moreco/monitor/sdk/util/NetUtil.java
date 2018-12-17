package io.github.weechang.moreco.monitor.sdk.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author zhangwei
 * date 2018/12/3
 * time 16:20
 */
@Slf4j
public class NetUtil {

    private static final String LOCALHOST_IP = "127.0.0.1";
    private static final String EMPTY_IP = "0.0.0.0";
    private static final Pattern IP_PATTERN = Pattern.compile("[0-9]{1,3}(\\.[0-9]{1,3}){3,}");
    public static final String Network_Name = getNetworkName();
    private static String hostIP;
    private static final String network_name_bond0 = "bond0";
    private static final String network_name_eth0 = "eth0";
    private static final String network_name_eth1 = "eth1";
    private static final String network_name_eth2 = "eth2";

    public NetUtil() {
    }

    public static String getLocalHostIP() {
        if (hostIP == null) {
            hostIP = getCurrentLocalHostIP();
        }

        return hostIP;
    }

    public static String getCurrentLocalHostIP() {
        String localIP = null;

        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            if (isValidHostAddress(localAddress)) {
                localIP = localAddress.getHostAddress();
            }
        } catch (Throwable var7) {
            log.warn("Failed to get ip address, " + var7.getMessage());
        }

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                ArrayList hostIPList = new ArrayList();

                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = (NetworkInterface) interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses != null) {
                            while (addresses.hasMoreElements()) {
                                try {
                                    InetAddress address = (InetAddress) addresses.nextElement();
                                    if (isValidHostAddress(address)) {
                                        hostIPList.add(address.getHostAddress());
                                    }
                                } catch (Throwable var6) {
                                    log.warn("Failed to get network card ip address. cause:" + var6.getMessage());
                                }
                            }
                        }
                    } catch (Throwable var8) {
                        log.warn("Failed to get network card ip address. cause:" + var8.getMessage());
                    }
                }

                if (localIP != null && hostIPList.contains(localIP)) {
                    return localIP;
                }

                return (String) hostIPList.get(0);
            }
        } catch (IOException var9) {
            log.error("Failed to get network card ip address. cause:" + var9.getMessage());
        }

        return localIP;
    }

    private static boolean isValidHostAddress(InetAddress address) {
        if (address != null && !address.isLoopbackAddress() && address.isSiteLocalAddress()) {
            String name = address.getHostAddress();
            return name != null && !"0.0.0.0".equals(name) && !"127.0.0.1".equals(name) && IP_PATTERN.matcher(name).matches();
        } else {
            return false;
        }
    }

    private static Map<String, String> getHostNames() throws SocketException {
        Map<String, String> hostNames = new HashMap();
        Enumeration interfaces = NetworkInterface.getNetworkInterfaces();

        while (true) {
            NetworkInterface network;
            Enumeration addresses;
            do {
                do {
                    if (!interfaces.hasMoreElements()) {
                        return hostNames;
                    }

                    network = (NetworkInterface) interfaces.nextElement();
                    log.debug("networkInterface name is:{},displayName is:{},isVirtual:{} ", new Object[]{network.getName(), network.getDisplayName(), network.isVirtual()});
                    addresses = network.getInetAddresses();
                } while (addresses == null);
            } while (network.isVirtual());

            while (addresses.hasMoreElements()) {
                try {
                    InetAddress address = (InetAddress) addresses.nextElement();
                    String ip = address.getHostAddress();
                    if (!"127.0.0.1".equals(ip) && !"Inet6Address".equals(address.getClass().getSimpleName())) {
                        log.info("Get to the valid IP address,host address is:{},class name is:{},networkInterface name is:{}", new Object[]{address.getHostAddress(), address.getClass().getSimpleName(), network.getName()});
                        if (ip != null && !"".equals(ip.trim())) {
                            hostNames.put(network.getName(), ip);
                        }
                    } else {
                        log.debug("IP address is not valid,host address is:{},class name is:{},networkInterface name is:{}", new Object[]{address.getHostAddress(), address.getClass().getSimpleName(), network.getName()});
                    }
                } catch (Throwable var6) {
                    log.warn("Failed to get network card ip address. cause:{}", var6.getMessage());
                }
            }
        }
    }

    public static String getInet4Address() {
        String hostName = null;

        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            hostName = localAddress.getHostAddress();
        } catch (Exception var4) {
            log.warn("get localhost ip on error:{}", var4.getMessage());
        }

        try {
            Map<String, String> hostNames = getHostNames();
            if (hostNames.size() <= 0) {
                return hostName;
            }

            if (hostNames.containsKey("bond0")) {
                return (String) hostNames.get("bond0");
            }

            if (hostNames.containsKey("eth0")) {
                return (String) hostNames.get("eth0");
            }

            if (hostNames.containsKey("eth1")) {
                return (String) hostNames.get("eth1");
            }

            if (hostNames.containsKey("eth2")) {
                return (String) hostNames.get("eth2");
            }

            Iterator i$ = hostNames.values().iterator();

            while (i$.hasNext()) {
                String ip = (String) i$.next();
                return ip;
            }
        } catch (Exception var5) {
            log.warn("Error getting IP address:{}", var5.getMessage());
        }

        return hostName;
    }

    public static String getNetworkName() {
        String networkName = "eth0";

        try {
            Map<String, String> hostNames = getHostNames();
            if (hostNames.size() <= 0) {
                return networkName;
            }

            if (hostNames.containsKey("bond0")) {
                return "bond0";
            }

            if (hostNames.containsKey("eth0")) {
                return "eth0";
            }

            if (hostNames.containsKey("eth1")) {
                return "eth1";
            }

            if (hostNames.containsKey("eth2")) {
                return "eth2";
            }

            Iterator i$ = hostNames.keySet().iterator();
            if (i$.hasNext()) {
                String name = (String) i$.next();
                return name;
            }
        } catch (Exception var4) {
            log.warn("Error getting IP address:{}", var4.getMessage());
        }

        return networkName;
    }
}
