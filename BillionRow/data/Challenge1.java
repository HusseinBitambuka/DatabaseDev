package BillionRow.data;

import java.io.*;
import java.util.*;

public class Challenge1 {
    static class Stats {
        int count = 0;
        double sum = 0, min = Double.MAX_VALUE, max = Double.MIN_VALUE;

        void add(double val) {
            sum += val;
            count++;
            min = Math.min(min, val);
            max = Math.max(max, val);
        }

        double avg() {
            return sum / count;
        }

        public String toString() {
            return String.format("%.1f/%.1f/%.1f", min, avg(), max);
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis() / 1000;
        int numRow = 0;

        Map<String, Stats> data = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("/home/husseinbitambuka/Dev/DatabaseDev/BillionRow/data/measurements.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numRow++;
                String[] parts = line.split(";");
                String station = parts[0];
                double temp = Double.parseDouble(parts[1]);
                data.computeIfAbsent(station, k -> new Stats()).add(temp);
            }
        }

        data.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));

        long endTime = System.currentTimeMillis() / 1000;

        System.out.println("the challenge contain " + numRow + " rows parsed and processed in "
                + (endTime - startTime) + " seconds ");
    }

}
