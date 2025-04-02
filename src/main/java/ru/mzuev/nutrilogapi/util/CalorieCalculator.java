package ru.mzuev.nutrilogapi.util;

import ru.mzuev.nutrilogapi.model.User;

/**
 * Утилитный класс для расчета дневной нормы калорий пользователя.
 */
public class CalorieCalculator {

    /**
     * Рассчитывает дневную норму калорий.
     *
     * @param user Пользователь
     * @return рассчитанная норма калорий
     */
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