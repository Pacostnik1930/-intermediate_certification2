import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Добавить новую игрушку в список.");
            System.out.println("2. Обновить вес (частоту) одной из игрушек.");
            System.out.println("3. Организовать розыгрыш игрушек.");
            System.out.println("4. Выйти из программы.");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите id игрушки: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите название игрушки: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите количество игрушек: ");
                    int count = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите вес игрушки (в % от 100): ");
                    double weight = scanner.nextDouble();
                    scanner.nextLine();
                    Toy.addToy(id, title, count, weight);
                    break;

                case 2:
                    System.out.print("Введите id игрушки, частоту которой нужно обновить: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите новый вес игрушки (в % от 100): ");
                    weight = scanner.nextDouble();
                    scanner.nextLine();
                    Toy.updateToy(id, weight);
                    break;

                case 3:
                    System.out.println("Розыгрыш игрушек:");
                    for (int i = 0; i < 10; ++i) {
                        Toy toy = Toy.getRandomToy();
                        System.out.println(toy.getTitle());
                    }
                    break;

                case 4:
                    System.out.println("Завершение программы.");
                    System.exit(0);

                default:
                    System.out.println("Некорректный выбор.");
                    break;
            }
        }
    }
}