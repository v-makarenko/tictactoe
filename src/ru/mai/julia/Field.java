package ru.mai.julia;


public class Field {
    private CellState[][] cells;

    private int width, height;

    public Field(FieldSize fieldSize) {

        this.width = width;
        this.height = height;
        cells = new CellState[width][height];
        for(int x = 0; x<width; x++){
            for(int y=0; y< height; y++){
                cells[x][y] = CellState.EMPTY;
            }
        }
    }


    public CellState getCellState(int x, int y){
        return cells[x][y];
    }

    public void setCellState(int x, int y, CellState cellState){
        if( x < width && y < height && getCellState(x, y) == CellState.EMPTY) {
            cells[x][y] = cellState;
        } else {
            // TODO выбросить ошибку
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
