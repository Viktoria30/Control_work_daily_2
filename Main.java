import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        toyStore.loadToys(); // Загрузим сохраненные игрушки (если есть)

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Добавить игрушку");
            System.out.println("2. Изменить вес игрушки");
            System.out.println("3. Розыгрыш приза");
            System.out.println("4. Сохранить игрушки");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Чтобы считать перевод строки
                    System.out.print("Введите название: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите количество: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Введите вес (%): ");
                    double weight = scanner.nextDouble();
                    Toy toy = new Toy(id, name, quantity, weight);
                    toyStore.addToy(toy);
                    break;
                case 2:
                    System.out.print("Введите ID игрушки, которую хотите изменить: ");
                    int toyId = scanner.nextInt();
                    System.out.print("Введите новый вес (%): ");
                    double newWeight = scanner.nextDouble();
                    toyStore.changeToyWeight(toyId, newWeight);
                    break;
                case 3:
                    Toy prize = toyStore.drawPrize();
                    if (prize != null && prize.getName() != null) {
                        System.out.println("Поздравляем! Вы выиграли " + prize.getName());
                    } else {
                        System.out.println("Извините, призы закончились.");
                    }
                    break;
                case 4:
                    toyStore.saveToys();
                    System.out.println("Игрушки сохранены.");
                    break;
                case 5:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
