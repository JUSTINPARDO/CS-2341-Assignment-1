package src;

import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static Steque<String> logQueue = new Steque<>(100000);
    public static Steque<String> errorStack = new Steque<>(100000);
    private static int errorCount = 0;
    private static int infoCount = 0;
    private static int warnCount = 0;
    private static int memoryCount = 0;

    public static void readLog(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                logQueue.enqueue(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Dequeue() {
        while (!logQueue.isEmpty()) {
            String currentLine = logQueue.deque();
            if (currentLine.contains("ERROR")) {
                errorStack.enqueue(currentLine);
                errorCount++;
            }
            else if (currentLine.contains("INFO"))
                infoCount++;

            else if (currentLine.contains("WARN")) {
                warnCount++;
                if (currentLine.contains("Memory"))
                    memoryCount++;
            }
        }

    }





    public static void main(String[] args) {
        readLog(new File( "src/log-data.csv"));
        StdOut.println("Number of Log Entries: " + logQueue.size());
        Dequeue();
        StdOut.println("ERROR count: " + errorCount);
        StdOut.println("INFO count: " + infoCount);
        StdOut.println("WARN count: " + warnCount);
        StdOut.println("Number of Memory Warnings: " + memoryCount);
        for (int i = 0; i < 100; i++) {
            errorStack.pop();
        }
    }
}


