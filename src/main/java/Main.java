import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int carQTY = 3; //количество автомобилей участвующих в гонке.
        Cars cars = new Cars(); // Гараж с автомобилями.
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать на 24 часа Ле-Мана!\nДавайте зарегистрируем автомобили.");

        while (carQTY > 0) {
            String name;
            int speed;
            while (true) {
                System.out.format("Вам осталось ввести %d авто.\nВведите имя машины:", carQTY);
                name = scanner.nextLine();
                if (!cars.isExist(name)) {
                    break;
                }
                System.out.format("'%s' уже существует, пожалуйста используйте уникальные имена.\n", name);
            }
            while (true) {
                System.out.format("Введите скорость автомобиля '%s'(от 0 до 250км/ч):", name);
                speed = Integer.parseInt(scanner.nextLine());
                if (speed <= 250 && speed >= 0) {
                    break;
                } else {
                    System.out.println("Вы ввели не верное значение.");
                }
            }
            cars.addCar(name, speed);
            carQTY--;
        }
        System.out.println("Все автомобили зарегистрированы, начинаем гонку.");
        Race race = new Race(cars);
        race.start();

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

}

class Race {
    Cars cars; // автомобили участвующие в гонке
    Car leader = new Car("", -1); // автомобиль выигравший гонку;
    int time = 24; // время гонки

    public Race(Cars cars) {
        this.cars = cars;
    }

    public void start() {
        for (Car car: cars.getInfo()){
            if (car.speed > leader.speed) {
                leader = car;
            }
        }
        System.out.format("Финиш\nПобедил автомобиль '%s'. Он проехал трассу за %d ч.", leader.name, time /leader.speed);
    }
}