import java.io.*;
import java.util.Scanner;

public class Toy {
    private int id;
    private String title;
    private int count;
    private double weight;
    private static final String FILE_NAME = "toys.txt";

    public Toy(int id, String title, int count, double weight) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static void addToy(int id, String title, int count, double weight) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        Toy toy = new Toy(id, title, count, weight);
        writer.write(toy.toString());
        writer.newLine();
        writer.close();
    }

    public static void updateToy(int id, double weight) throws IOException {
        File file = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) == id) {
                Toy toy = new Toy(id, parts[1], Integer.parseInt(parts[2]), weight);
                writer.write(toy.toString());
                writer.newLine();
            } else {
                writer.write(line);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();

        file.delete();
        tempFile.renameTo(file);
    }

    public static Toy getRandomToy() throws IOException {
        double totalWeight = 0;
        Scanner scanner = new Scanner(new File(FILE_NAME));
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(",");
            totalWeight += Double.parseDouble(parts[3]);
        }

        double randomWeight = Math.random() * totalWeight;
        scanner = new Scanner(new File(FILE_NAME));
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(",");
            randomWeight -= Double.parseDouble(parts[3]);
            if (randomWeight <= 0) {
                scanner.close();
                return new Toy(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
            }
        }

        scanner.close();
        return null;
    }

    public String toString() {
        return id + "," + title + "," + count + "," + weight;
    }
}
