package com.sky.hashcode.streamVideo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    private int[][] cacheToEndLatency;
    private int[] centerToEndLatency;
    private int[] videoSize;

    private List<EndPoint> endPointList = new ArrayList<>();
    private List<Cache> cacheList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        new Main().start(new File("JavaExercise/src/resources/streamVideo/me_at_the_zoo.in"));
        new Main().start(new File("JavaExercise/src/resources/streamVideo/kittens.in"));
        new Main().start(new File("JavaExercise/src/resources/streamVideo/trending_today.in"));
        new Main().start(new File("JavaExercise/src/resources/streamVideo/videos_worth_spreading.in"));
    }

    private void start(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
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
        for(int i = 0; i < endPointCount; i++) {
            EndPoint endPoint = new EndPoint(i);
            endPointList.add(endPoint);
            centerToEndLatency[i] = scanner.nextInt();
            int connectedCacheCount = scanner.nextInt();
            scanner.nextLine();
            for (int j = 0; j < connectedCacheCount; j ++) {
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

    private Map<Integer, Set<Cache>> videoCacheMap = new HashMap<>();

    private void process() {
        List<ValueElement> elements = new ArrayList<>();
        for (int cacheId = 0; cacheId < cacheList.size(); cacheId ++) {
            elements.clear();
            for (int videoId = 0; videoId < videoSize.length; videoId ++) {
                elements.add(computeValue(videoId, cacheId));
            }
            Collections.sort(elements);
            //System.out.println(elements);
            Cache cache = cacheList.get(cacheId);
            int maxIndex = 0;
            while(maxIndex < elements.size()) {
                if (cache.restCapacity >= videoSize[elements.get(maxIndex).videoId]) {
                    cacheVideo(elements.get(maxIndex).videoId, cache);
                    if (cache.restCapacity <= 0) {
                        break;
                    }
                }
                maxIndex++;
            }
        }
    }

    private void cacheVideo(int videoId, Cache cache) {
        cache.videoFilled.add(videoId);
        cache.restCapacity -= videoSize[videoId];
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
            if (! cacheList.get(i).videoFilled.isEmpty()) {
                cacheUsedCount ++;
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
            writer = new PrintWriter(file.getAbsolutePath().replace(".in", ".out"));
            writer.print(sb.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private ValueElement computeValue(int videoId, int cacheId) {
        double value = 0;
        for (EndPoint endPoint : endPointList) {
            int latency = cacheToEndLatency[cacheId][endPoint.id];
            if (latency > 0 && endPoint.videoRequestMap.containsKey(videoId) && !isUseless(videoId, endPoint, latency)) {
                value += endPoint.videoRequestMap.get(videoId) * (centerToEndLatency[endPoint.id] - latency);
            }
        }
        return new ValueElement(videoId, value / (double) videoSize[videoId]);
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

    private static class ValueElement implements Comparable<ValueElement> {
        private int videoId;
        private double value;

        public ValueElement(int videoId, double value) {
            this.videoId = videoId;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                    "videoId=" + videoId +
                    ", value=" + value +
                    '}';
        }

        @Override
        public int compareTo(ValueElement o) {
            return Double.compare(o.value, value);
        }
    }
}
