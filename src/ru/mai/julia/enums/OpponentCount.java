package ru.mai.julia.enums;

public enum OpponentCount {
    COUNT_1, COUNT_2;

    public static OpponentCount parse(String count) {
        try {
            int intCount = Integer.parseInt(count.trim());
            switch (intCount) {
                case 1:
                    return COUNT_1;
                case 2:
                    return COUNT_2;
                default:
                    System.out.println("Количество соперников может быть 1 или 2!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели некорректное число. Попытайтесь снова!");
        }
        return null;
    }
}
