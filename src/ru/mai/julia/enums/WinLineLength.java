package ru.mai.julia.enums;


import java.io.Serializable;

// длина линии, которую надо собрать, чтобы выиграть
public enum WinLineLength implements Serializable {
    LENGTH_3(3), LENGTH_4(4), LENGTH_5(5);

    WinLineLength(int length) {
        this.length = length;
    }

    private int length;

    public int getLength() {
        return length;
    }

    public static WinLineLength parse(String option) {
        try {
            int intCount = Integer.parseInt(option.trim());
            switch (intCount) {
                case 1:
                    return LENGTH_3;
                case 2:
                    return LENGTH_4;
                case 3:
                    return LENGTH_5;
                default:
                    System.out.println("Выбирайте из предложенных вариантов!!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели некорректное число. Попытайтесь снова!");
        }
        return null;
    }
}
