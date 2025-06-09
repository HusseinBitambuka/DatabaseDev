package BillionRow.data;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

public class MemoryMappedParallel {

    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        File file = new File("/home/husseinbitambuka/Dev/DatabaseDev/BillionRow/data/measurements.txt");
        long fileSize = file.length();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<ParseResult>> futures = new ArrayList<>();

        long chunkSize = fileSize / THREAD_COUNT;
        long startTime = System.currentTimeMillis();
        int totalRows = 0;

        for (int i = 0; i < THREAD_COUNT; i++) {
            long start = i * chunkSize;
            long end = (i == THREAD_COUNT - 1) ? fileSize : (start + chunkSize);
            int threadId = i;

            futures.add(executor.submit(() -> processChunk(file, start, end, threadId == 0)));
        }

        Map<String, Stats> finalMap = new HashMap<>();

        for (Future<ParseResult> future : futures) {
            ParseResult result = future.get();
            totalRows += result.rows;
            for (Map.Entry<String, Stats> entry : result.data.entrySet()) {
                finalMap.computeIfAbsent(entry.getKey(), k -> new Stats()).merge(entry.getValue());
            }
        }

        executor.shutdown();

        finalMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));

        long endTime = System.currentTimeMillis();
        System.out.println("Parsed " + totalRows + " rows in " + ((endTime - startTime) / 1000) + " seconds.");
    }

    private static ParseResult processChunk(File file, long start, long end, boolean isFirstChunk) throws IOException {
        Map<String, Stats> data = new HashMap<>();
        int numRow = 0;

        try (FileChannel channel = new FileInputStream(file).getChannel()) {
            long mapSize = end - start;
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, start, mapSize);

            byte[] lineBuffer = new byte[256];
            int pos = 0;

            if (!isFirstChunk) {
                while (buffer.hasRemaining() && buffer.get() != '\n') {
                }
            }

            while (buffer.hasRemaining()) {
                byte b = buffer.get();
                if (b == '\n') {
                    if (pos > 0 && lineBuffer[pos - 1] == '\r')
                        pos--;

                    String line = new String(lineBuffer, 0, pos, StandardCharsets.UTF_8);
                    int sep = line.indexOf(';');
                    if (sep != -1) {
                        try {
                            String city = line.substring(0, sep);
                            double temp = Double.parseDouble(line.substring(sep + 1));
                            data.computeIfAbsent(city, k -> new Stats()).add(temp);
                            numRow++;
                        } catch (NumberFormatException ignored) {
                        }
                    }

                    pos = 0;
                } else {
                    if (pos >= lineBuffer.length) {
                        lineBuffer = Arrays.copyOf(lineBuffer, lineBuffer.length * 2);
                    }
                    lineBuffer[pos++] = b;
                }
            }

            // Final line
            if (pos > 0) {
                String line = new String(lineBuffer, 0, pos, StandardCharsets.UTF_8);
                int sep = line.indexOf(';');
                if (sep != -1) {
                    try {
                        String city = line.substring(0, sep);
                        double temp = Double.parseDouble(line.substring(sep + 1));
                        data.computeIfAbsent(city, k -> new Stats()).add(temp);
                        numRow++;
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }

        return new ParseResult(data, numRow);
    }
}

// Helper class to return both stats and row count
class ParseResult {
    Map<String, Stats> data;
    int rows;

    ParseResult(Map<String, Stats> data, int rows) {
        this.data = data;
        this.rows = rows;
    }
}
