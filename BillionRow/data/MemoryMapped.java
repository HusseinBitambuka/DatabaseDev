package BillionRow.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MemoryMapped {

    public static void main(String[] args) throws IOException {
        Map<String, Stats> data = new HashMap<>();

        File file = new File("/home/husseinbitambuka/Dev/DatabaseDev/BillionRow/data/measurements.txt");
        try (FileChannel channel = new FileInputStream(file).getChannel()) {
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            byte[] lineBuffer = new byte[256];
            int pos = 0;
            int numRow = 0;

            while (buffer.hasRemaining()) {
                byte b = buffer.get();

                if (b == '\n') {
                    if (pos > 0 && lineBuffer[pos - 1] == '\r')
                        pos--; // Handle \r\n

                    String line = new String(lineBuffer, 0, pos, StandardCharsets.UTF_8);
                    int sep = line.indexOf(';');
                    String station = line.substring(0, sep);
                    double temp = Double.parseDouble(line.substring(sep + 1));
                    data.computeIfAbsent(station, k -> new Stats()).add(temp);
                    pos = 0;
                    numRow++;
                } else {
                    if (pos >= lineBuffer.length) {
                        lineBuffer = Arrays.copyOf(lineBuffer, lineBuffer.length * 2);
                    }
                    lineBuffer[pos++] = b;
                }
            }

            // Handle final line if no trailing newline
            if (pos > 0) {
                String line = new String(lineBuffer, 0, pos, StandardCharsets.UTF_8);
                int sep = line.indexOf(';');
                String station = line.substring(0, sep);
                double temp = Double.parseDouble(line.substring(sep + 1));
                data.computeIfAbsent(station, k -> new Stats()).add(temp);
                numRow++;
            }
        }

        data.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));
    }
}
