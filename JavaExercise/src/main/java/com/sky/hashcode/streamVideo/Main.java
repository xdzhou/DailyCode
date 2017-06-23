package com.sky.hashcode.streamVideo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import com.google.common.base.Preconditions;

public class Main {
    private static final Comparator<ValueElement> VALUE_ELEMENT_COMPARATOR = (v1, v2) -> (Double.compare(v1.value, v2.value));
    private int[][] cacheToEndLatency;
    private int[] centerToEndLatency;
    private int[] videoSize;
    private List<EndPoint> endPointList = new ArrayList<>();
    private List<Cache> cacheList = new ArrayList<>();
    private Map<Integer, Set<Cache>> videoCacheMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        //new Main().start(new File("JavaExercise/src/resources/streamVideo/me_at_the_zoo.in"));
        //new Main().start(new File("JavaExercise/src/resources/streamVideo/kittens.in"));
        new Main().start(new File("src/main/resources/hashcode/streamVideo/trending_today.in"));
        //new Main().start(new File("JavaExercise/src/resources/streamVideo/videos_worth_spreading.in"));
    }

    private void start(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file, "UTF-8");
        videoSize = new int[scanner.nextInt()];
        int endPointCount = scanner.nextInt();
        centerToEndLatency = new int[endPointCount];
        int requestDesc = scanner.nextInt();
        int cacheCount = scanner.nextInt();
        //
        cacheToEndLatency = new int[cacheCount][endPointCount];
        Cache.CAPACITY = scanner.nextInt();
        for (int i = 0; i < cacheCount; i++) {
            cacheList.add(new Cache(i));
        }
        scanner.nextLine();
        for (int i = 0; i < videoSize.length; i++) {
            videoSize[i] = scanner.nextInt();
        }
        scanner.nextLine();
        for (int i = 0; i < endPointCount; i++) {
            EndPoint endPoint = new EndPoint(i);
            endPointList.add(endPoint);
            centerToEndLatency[i] = scanner.nextInt();
            int connectedCacheCount = scanner.nextInt();
            scanner.nextLine();
            for (int j = 0; j < connectedCacheCount; j++) {
                int cacheId = scanner.nextInt();
                int latency = scanner.nextInt();
                cacheToEndLatency[cacheId][i] = latency;
                scanner.nextLine();
            }
        }
        for (int i = 0; i < requestDesc; i++) {
            int videoId = scanner.nextInt();
            int endPointId = scanner.nextInt();
            int requestCount = scanner.nextInt();
            scanner.nextLine();
            endPointList.get(endPointId).videoRequestMap.put(videoId, requestCount);
        }
        scanner.close();
        process();
        printResult(file);
    }

    private void process() {
        PriorityQueue<ValueElement> priorityQueue = new PriorityQueue<>(cacheList.size() * videoSize.length, VALUE_ELEMENT_COMPARATOR);
        ValueElement[][] valueTable = new ValueElement[cacheList.size()][videoSize.length];
        for (int cacheId = 0; cacheId < cacheList.size(); cacheId++) {
            for (int videoId = 0; videoId < videoSize.length; videoId++) {
                ValueElement ele = computeValue(videoId, cacheId);
                valueTable[cacheId][videoId] = ele;
                if (ele != null) {
                    priorityQueue.add(ele);
                }
            }
        }
        while (!priorityQueue.isEmpty()) {
            ValueElement best = priorityQueue.poll();
            cacheVideo(best.videoId, best.cacheId);
            //remove self
            valueTable[best.cacheId][best.videoId] = null;
            //update value
            for (int i = 0; i < videoSize.length; i++) {
                ValueElement ele = valueTable[best.cacheId][i];
                if (ele != null) {
                    ValueElement newEle = computeValue(ele.videoId, ele.cacheId);
                    if (newEle == null) {
                        priorityQueue.remove(ele);
                    } else if (newEle.value != ele.value) {
                        priorityQueue.remove(ele);
                        priorityQueue.add(newEle);
                    }
                    valueTable[best.cacheId][i] = newEle;
                }
            }
            for (int i = 0; i < cacheList.size(); i++) {
                ValueElement ele = valueTable[best.cacheId][i];
                if (ele != null) {
                    ValueElement newEle = computeValue(ele.videoId, ele.cacheId);
                    if (newEle == null) {
                        priorityQueue.remove(ele);
                    } else if (newEle.value != ele.value) {
                        priorityQueue.remove(ele);
                        priorityQueue.add(newEle);
                    }
                    valueTable[best.cacheId][i] = newEle;
                }
            }
        }
    }

    private void cacheVideo(int videoId, int cacheId) {
        Cache cache = cacheList.get(cacheId);
        cache.videoFilled.add(videoId);
        cache.restCapacity -= videoSize[videoId];
        Preconditions.checkState(cache.restCapacity >= 0);
        Set<Cache> caches = videoCacheMap.get(videoId);
        if (caches == null) {
            caches = new HashSet<>();
            videoCacheMap.put(videoId, caches);
        }
        caches.add(cache);
    }

    private void printResult(File file) {
        StringBuilder sb = new StringBuilder();
        int cacheUsedCount = 0;
        for (int i = 0; i < cacheList.size(); i++) {
            if (!cacheList.get(i).videoFilled.isEmpty()) {
                cacheUsedCount++;
                sb.append(i).append(' ');
                for (int videoId : cacheList.get(i).videoFilled) {
                    sb.append(videoId).append(' ');
                }
                sb.delete(sb.length() - 1, sb.length());
                sb.append('\n');
            }
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.insert(0, '\n');
        sb.insert(0, cacheUsedCount);

        System.out.println(sb.toString());

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file.getAbsolutePath().replace(".in", ".out"), "UTF-8");
            writer.print(sb.toString());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private ValueElement computeValue(int videoId, int cacheId) {
        double value = 0d;
        if (videoSize[videoId] <= cacheList.get(cacheId).restCapacity) {
            for (EndPoint endPoint : endPointList) {
                int latency = cacheToEndLatency[cacheId][endPoint.id];
                if (latency > 0 &&
                    endPoint.videoRequestMap.containsKey(videoId) &&
                    !isUseless(videoId, endPoint, latency)) {
                    value += endPoint.videoRequestMap.get(videoId) * (centerToEndLatency[endPoint.id] - latency);
                }
            }
            Preconditions.checkState(value >= 0);
        }
        return value == 0d ? null : new ValueElement(videoId, cacheId, value / (double) videoSize[videoId]);
    }

    private boolean isUseless(int videoId, EndPoint endPoint, int curLatency) {
        Set<Cache> caches = videoCacheMap.get(videoId);
        if (caches != null) {
            for (Cache c : caches) {
                if (cacheToEndLatency[c.id][endPoint.id] <= curLatency) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class ValueElement {
        private int videoId;
        private int cacheId;
        private double value;

        private ValueElement(int videoId, int cacheId, double value) {
            this.videoId = videoId;
            this.cacheId = cacheId;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                "videoId=" + videoId +
                ", value=" + value +
                '}';
        }
    }
}
