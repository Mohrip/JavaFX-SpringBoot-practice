package com.JavaFxAPPS.JavaFxAPPS;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.ManagementFactory;

import java.util.HashMap;
import java.util.Map;




@Service
public class HardwareUsageService {

    public Map<String, Object> getHardwareUsage(){
        com.sun.management.OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        Map<String, Object> usage = new HashMap<>();
        usage.put("cpuLoad", osBean.getSystemCpuLoad());
        usage.put("TotalMemory", osBean.getTotalPhysicalMemorySize());
        usage.put("FreeMemory", osBean.getFreePhysicalMemorySize());
        usage.put("UsedMemory", osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize());
        usage.put("AvailableProcessors", osBean.getAvailableProcessors());
        usage.put("CommittedVirtualMemorySize", osBean.getCommittedVirtualMemorySize());
        usage.put("TotalSwapSpaceSize", osBean.getTotalSwapSpaceSize());
        usage.put("FreeSwapSpaceSize", osBean.getFreeSwapSpaceSize());
        usage.put("UsedSwapSpaceSize", osBean.getTotalSwapSpaceSize() - osBean.getFreeSwapSpaceSize());
        usage.put("ProcessCount", osBean.getProcessCpuLoad());

        usage.put("SystemLoadAverage", osBean.getSystemLoadAverage());
        return usage;

    }
}


// This service provides methods to retrieve usage statistics such as CPU load, memory usage, and swap space.
