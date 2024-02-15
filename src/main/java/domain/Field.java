package domain;

import java.util.ArrayList;
import java.util.List;
import utils.NumberGenerator;

public class Field {
    private final CarFactory carFactory;
    private final StringBuilder record;
    private final NumberGenerator numberGenerator;

    public Field(CarFactory carFactory, NumberGenerator numberGenerator) {
        this.carFactory = carFactory;
        this.record = new StringBuilder("\n실행 결과\n");
        this.numberGenerator = numberGenerator;
    }

    public void race(int numberOfAttempts) {
        List<Car> cars = carFactory.getAllCars();
        for (int i = 0; i < numberOfAttempts; i++) {
            runSingleAttempt(cars);
            log(cars);
        }
    }

    private void runSingleAttempt(List<Car> cars) {
        for (Car car : cars) {
            int randomNumber = numberGenerator.getNumber();
            car.execute(randomNumber);
        }
    }

    private void log(List<Car> cars) {
        for (Car car : cars) {
            record.append(car.toString());
            record.append("\n");
        }
        record.append("\n");
    }

    public String getResult() {
        List<Car> winners = carFactory.getWinners();
        List<String> winnerNames = new ArrayList<>();
        for (Car winner : winners) {
            winnerNames.add(winner.getName());
        }
        String winnerResult = String.join(", ", winnerNames).concat("가 최종 우승했습니다.");
        return record.append(winnerResult).toString();
    }
}
