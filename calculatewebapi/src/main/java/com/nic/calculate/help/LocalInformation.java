package com.nic.calculate.help;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class LocalInformation {
    private static String machineIp = null;

    public static String getMachineIp() {
        if (machineIp == null || machineIp.length() == 0) {
            InetAddress[] addrList;
            try {
                Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) interfaces.nextElement();
                    Enumeration ipAddrEnum = ni.getInetAddresses();
                    while (ipAddrEnum.hasMoreElements()) {
                        InetAddress addr = (InetAddress) ipAddrEnum.nextElement();
                        String machineIpTmp = addr.getHostAddress();
                        if (!machineIpTmp.equals("127.0.0.1") && machineIpTmp.length() < 15) {
                            machineIp = machineIpTmp;
                            machineName = addr.getHostName();
                            return machineIp;
                        }
                    }
                }
            } catch (Exception ex) {

            }
        }
        return machineIp;
    }

    private static String machineName = null;

    public static String getMachineName() {
        if (machineName == null || machineName.length() == 0) {
            try {
                machineName = InetAddress.getLocalHost().getHostName();
            } catch (Exception ex) {

            }
        }
        return machineName;
    }
}
