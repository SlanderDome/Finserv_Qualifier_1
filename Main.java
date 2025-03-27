import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Read JSON file as a string
        String json = readFile("input.json");

        // Extract first_name and roll_number using basic string operations
        String firstName = extractValue(json, "first_name").toLowerCase().replace(" ", "");
        String rollNumber = extractValue(json, "roll_number").toLowerCase().replace(" ", "");

        // Generate MD5 hash
        String hash = md5(firstName + rollNumber);

        // Write hash to output.txt
        FileWriter writer = new FileWriter("output.txt");
        writer.write(hash);
        writer.close();

        System.out.println("MD5 Hash: " + hash);
    }

    // Function to read file content
    public static String readFile(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();
        return sb.toString();
    }

    // Function to extract JSON values manually
    public static String extractValue(String json, String key) {
        int start = json.indexOf("\"" + key + "\":") + key.length() + 4;
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }

    // MD5 hash function
    public static String md5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
