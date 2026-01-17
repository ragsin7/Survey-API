package com.propsur.api.project.service.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MachineInformation {

    private static final Logger logger = LoggerFactory.getLogger(MachineInformation.class);

    public static String getClientIpMacAddr(String ipMac) {
        String ipNoMacAddr = "";
        if ("I".equals(ipMac)) {
            try {
                ipNoMacAddr = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                logger.error("Error retrieving IP address", e);
                return ipNoMacAddr;
            }
        } else if ("M".equals(ipMac)) {
            try {
                String osName = System.getProperty("os.name");
                logger.info("OS Name: {}", osName);

                if (osName.contains("Windows")) {
                    ipNoMacAddr = getMacForWindows();
                } else {
                    ipNoMacAddr = getMacForLinux("eth0");
                    if (ipNoMacAddr == null) ipNoMacAddr = getMacForLinux("eth1");
                    if (ipNoMacAddr == null) ipNoMacAddr = getMacForLinux("eth2");
                    if (ipNoMacAddr == null) ipNoMacAddr = getMacForLinux("usb0");
                }
            } catch (Exception e) {
                logger.error("Error retrieving MAC address", e);
                return ipNoMacAddr;
            }
        }
        return ipNoMacAddr;
    }

    private static String getMacForLinux(String name) {
        try {
            NetworkInterface network = NetworkInterface.getByName(name);
            if (network == null) {
                logger.warn("No network interface found for: {}", name);
                return null;
            }
            byte[] mac = network.getHardwareAddress();
            if (mac == null) {
                logger.warn("No hardware address found for network: {}", name);
                return null;
            }
            return convertMacBytesToString(mac);
        } catch (Exception e) {
            logger.error("Error retrieving MAC address for Linux: {}", name, e);
            return null;
        }
    }

    private static String getMacForWindows() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(addr);
            if (network == null) {
                logger.warn("No network interface found for address: {}", addr);
                return null;
            }
            byte[] mac = network.getHardwareAddress();
            if (mac == null) {
                logger.warn("No hardware address found for network: {}", addr);
                return null;
            }
            return convertMacBytesToString(mac);
        } catch (Exception e) {
            logger.error("Error retrieving MAC address for Windows", e);
            return null;
        }
    }

    private static String convertMacBytesToString(byte[] mac) {
        return mac == null ? null : 
            String.join("-", 
                java.util.stream.IntStream.range(0, mac.length)
                    .mapToObj(i -> String.format("%02X", mac[i]))
                    .toArray(String[]::new));
    }

    public static void main(String[] args) {
        System.out.println("IP Address: " + getClientIpMacAddr("I"));
        System.out.println("MAC Address: " + getClientIpMacAddr("M"));
    }
}
