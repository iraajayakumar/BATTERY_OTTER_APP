package com.example.battery_otter_app;
/*
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.net.NetworkStats;
import android.net.NetworkStatsManager;
import android.net.NetworkCapabilities;
import android.os.RemoteException;

import java.util.List;

public class DataExtractor {

    private Context context;

    public DataExtractor(Context context) {
        this.context = context;
    }

    public long getTotalUsageTime() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        long endTime = System.currentTimeMillis();
        long startTime = endTime - (24 * 60 * 60 * 1000); // 24 hours ago
        long totalScreenTime = 0;

        List<UsageStats> usageStatsList = usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, startTime, endTime);

        for (UsageStats usageStats : usageStatsList) {
            totalScreenTime += usageStats.getTotalTimeInForeground();
        }
        return totalScreenTime / (1000 * 60); // Return time in minutes
    }

    public long getTotalDataUsage() {
        NetworkStatsManager networkStatsManager = (NetworkStatsManager) context.getSystemService(Context.NETWORK_STATS_SERVICE);
        long endTime = System.currentTimeMillis();
        long startTime = endTime - (24 * 60 * 60 * 1000); // 24 hours ago
        long totalDataUsage = 0;

        try {
            NetworkStats mobileStats = networkStatsManager.querySummary(NetworkCapabilities.TRANSPORT_CELLULAR, null, startTime, endTime);
            NetworkStats.Bucket bucket = new NetworkStats.Bucket();

            while (mobileStats.hasNextBucket()) {
                mobileStats.getNextBucket(bucket);
                totalDataUsage += bucket.getRxBytes() + bucket.getTxBytes();
            }

            NetworkStats wifiStats = networkStatsManager.querySummary(NetworkCapabilities.TRANSPORT_WIFI, null, startTime, endTime);
            while (wifiStats.hasNextBucket()) {
                wifiStats.getNextBucket(bucket);
                totalDataUsage += bucket.getRxBytes() + bucket.getTxBytes();
            }

            return totalDataUsage / (1024 * 1024); // Return data in MB
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }
}*/
