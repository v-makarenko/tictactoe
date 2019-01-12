package ru.mai.julia;

public enum FieldSize {
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
}
