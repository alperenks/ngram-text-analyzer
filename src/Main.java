import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        int[] nGramSizes = {1, 2, 3};

        String[] files = {
                "BİLİM İŞ BAŞINDA.txt",
                "grimms-fairy-tales_1.txt",
                "grimms-fairy-tales_2.txt",
                "grimms-fairy-tales_3.txt",
                "grimms-fairy-tales_4.txt",
                "grimms-fairy-tales_5.txt",
                "grimms-fairy-tales_6.txt",
                "DEĞİŞİM.txt",
                "grimms-fairy-tales_7.txt"
        };

        try (BufferedWriter reportWriter = new BufferedWriter(new FileWriter("report.txt"))) {
            for (String file : files) {
                System.out.println("Analyzing file: " + file);
                reportWriter.write("Analyzing file: " + file + "\n");
                String text = readFile(file);

                for (int n : nGramSizes) {
                    long startTime = System.nanoTime();
                    System.out.println("Generating " + n + "-grams right now");
                    reportWriter.write("Generating " + n + "-grams right now\n");

                    Map<String, Integer> nGrams = generateNGrams(text, n);

                    List<Map.Entry<String, Integer>> topN = getTopN(nGrams, 10);
                    for (Map.Entry<String, Integer> entry : topN) {
                        reportWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
                    }

                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime);
                    System.out.println("Time taken for " + n + "-grams: " + duration / 1_000_000 + " ms\n");
                    reportWriter.write("Time taken for " + n + "-grams: " + duration / 1_000_000 + " ms\n\n");
                }
            }
        }
    }

    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(" ");
            }
        }

        String text = content.toString().toLowerCase();
        text = text.replaceAll("[^\\w\\s]", "");
        return text;
    }

    public static Map<String, Integer> generateNGrams(String text, int n) {
        String[] tokens = text.split("\\W+");
        Map<String, Integer> nGramCounts = new HashMap<>();

        for (int i = 0; i <= tokens.length - n; i++) {
            String nGram = String.join(" ", Arrays.copyOfRange(tokens, i, i + n));
            nGramCounts.put(nGram, nGramCounts.getOrDefault(nGram, 0) + 1);
        }
        return nGramCounts;
    }

    public static void printTopN(Map<String, Integer> map, int n) {
        map.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(n)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    public static List<Map.Entry<String, Integer>> getTopN(Map<String, Integer> map, int n) {
        return map.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(n)
                .toList();
    }
}