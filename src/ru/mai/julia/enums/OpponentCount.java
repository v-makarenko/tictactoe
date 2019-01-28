package ru.mai.julia.enums;

import java.io.Serializable;

public enum OpponentCount implements Serializable {
    COUNT_1(1), COUNT_2(2);

    int count;

    OpponentCount(int count) {
        this.count = count;
    }

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

    public int getCount() {
        return count;
    }
}
