package ru.mai.julia.enums;

import java.io.Serializable;

public enum FieldSize implements Serializable {
    FIELD_3X3(3, 3), FIELD_9X9(9, 9), FIELD_15X15(15, 15);

    private int width, height;

    FieldSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static FieldSize parse(String option) {
        try {
            int intCount = Integer.parseInt(option.trim());
            switch (intCount) {
                case 1:
                    return FIELD_3X3;
                case 2:
                    return FIELD_9X9;
                case 3:
                    return FIELD_15X15;
                default:
                    System.out.println("Выбирайте из предложенных вариантов!!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели некорректное число. Попытайтесь снова!");
        }
        return null;
    }
}
