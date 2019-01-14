package ru.mai.julia.enums;


// длина линии, которую надо собрать, чтобы выиграть
public enum WinLineLength {
    LENGTH_3, LENGTH_4, LENGTH_5;


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
