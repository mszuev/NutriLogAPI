package ru.mzuev.nutrilogapi.util;

import ru.mzuev.nutrilogapi.model.User;

public class CalorieCalculator {

    public static double calculate(User user) {
        double bmr = 88.36 + (13.4 * user.getWeight())
                + (4.8 * user.getHeight())
                - (5.7 * user.getAge());

        return switch (user.getTargetType()) {
            case WEIGHT_LOSS -> bmr * 0.85;
            case MAINTENANCE -> bmr;
            case WEIGHT_GAIN -> bmr * 1.15;
        };
    }
}