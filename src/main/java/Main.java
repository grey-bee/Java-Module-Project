import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int carQTY = 3; //количество автомобилей участвующих в гонке.
        Cars cars = new Cars(); // Гараж с автомобилями.
        cars.registration(carQTY); //регистрация машин.
        Race race = new Race(cars); //регистрация гонки.
        race.start(); //запуск гонки
    }
}

class Car {
    String name;
    int speed;

    public Car(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }
}

class Cars {
    ArrayList<Car> cars = new ArrayList<>();

    public ArrayList<Car> getInfo() {
        return cars;
    }

    public void registration(int qty) {
        System.out.println("Добро пожаловать на 24 часа Ле-Мана!\nДавайте зарегистрируем автомобили.");
        Scanner scanner = new Scanner(System.in);
        while (qty > 0) {

            String name;
            String speed;
            int speedInt;

            while (true) {
                System.out.format("\nВам осталось ввести %d авто.\nВведите имя машины:", qty);
                name = scanner.nextLine();
                if (!this.isExist(name) && !name.trim().isEmpty()) {
                    break;
                }
                if (name.trim().isEmpty()) {
                    System.out.println("имя не может быть пустым.\n");
                } else {
                    System.out.format("'%s' уже существует, пожалуйста используйте уникальные имена.\n", name);
                }
            }
            while (true) {
                System.out.format("Введите скорость автомобиля '%s'(от 0 до 250км/ч):", name);
                speed = scanner.nextLine();
                if (canConvertToInt(speed)) {
                    speedInt = Integer.parseInt(speed);
                    if (speedInt >= 0 && speedInt <= 250) {
                        break;
                    }
                }
                System.out.println("Вы ввели не верное значение.");
            }
            this.addCar(name, speedInt);
            qty--;
        }
    }


    public boolean isExist(String name) {
        for (Car existingCar : cars) {
            if (existingCar.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addCar(String name, int speed) {
        cars.add(new Car(name, speed));
    }

    boolean canConvertToInt(String str) {
        return str.matches("-?\\d+");
    }

}

class Race {
    Cars cars; // автомобили участвующие в гонке
    Car leader = new Car("", -1); // автомобиль выигравший гонку;
    int time = 24; // время гонки

    public Race(Cars cars) {
        this.cars = cars;
    }

    public void start() {
        System.out.println("Все автомобили зарегистрированы, начинаем гонку.");
        for (Car car : cars.getInfo()) {
            if (car.speed > leader.speed) {
                leader = car;
            }
        }
        System.out.format("Финиш\nПобедил автомобиль '%s'. Он проехал %dкм за 24 часа.", leader.name, time * leader.speed);
    }
}