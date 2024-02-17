package domain;

import utils.NumberGenerator;
import view.InputView;
import view.OutputView;

import java.io.IOException;

public class RaceManager {
    private final InputView inputView;
    private final Field field;
    private final int numberOfAttempts;

    public RaceManager(InputView inputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        CarGroup carGroup = repeatUntilGetValidCarNames();
        this.numberOfAttempts = repeatUntilGetValidNumberOfAttempts();
        this.field = new Field(carGroup, numberGenerator);
    }

    private CarGroup repeatUntilGetValidCarNames() {
        try {
            String carNames = inputView.readCarNames();
            return new CarGroup(carNames);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeatUntilGetValidCarNames();
        }
    }

    private int repeatUntilGetValidNumberOfAttempts() {
        try {
            return inputView.readNumberOfAttempts();
        } catch (IOException | IllegalArgumentException e) {
            return repeatUntilGetValidNumberOfAttempts();
        }
    }

    public void run() {
        field.race(numberOfAttempts);
        String raceResult = field.getResult();
        OutputView.printResult(raceResult);
    }
}
