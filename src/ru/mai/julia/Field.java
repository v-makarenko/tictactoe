package ru.mai.julia;


import ru.mai.julia.enums.CellState;
import ru.mai.julia.enums.FieldSize;
import ru.mai.julia.enums.WinLineLength;

import java.io.Serializable;

public class Field implements Serializable {
    private CellState[][] cells;

    private int width, height;

    public Field(FieldSize fieldSize) {
        this.width = fieldSize.getWidth();
        this.height = fieldSize.getHeight();

        cells = new CellState[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = CellState.EMPTY;
            }
        }
    }


    public CellState getCellState(int x, int y) {
        return cells[x][y];
    }

    public void setCellState(int x, int y, CellState cellState) {
        // чтобы клетка (1,1) проецировалась на элемент массива (0,0)
        x--;
        y--;
        if (x < width && y < height && getCellState(x, y) == CellState.EMPTY) {
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


    public CellState checkVictory(WinLineLength winLineLength) {
        CellState cellState = null;
        int similarCells = 0;
        final int targetSimilarCells = winLineLength.getLength();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (getCellState(i, j) == cellState) {
                    similarCells++;
                } else {
                    cellState = getCellState(i, j);
                    similarCells = 1;
                }
                if (cellState != CellState.EMPTY
                        && similarCells == targetSimilarCells) {
                    return cellState;
                }
            }
            cellState = null;
            similarCells = 0;
        }

        cellState = null;
        similarCells = 0;

        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                if (getCellState(i, j) == cellState) {
                    similarCells++;
                } else {
                    cellState = getCellState(i, j);
                    similarCells = 1;
                }
                if (cellState != CellState.EMPTY
                        && similarCells == targetSimilarCells) {
                    return cellState;
                }
            }
            cellState = null;
            similarCells = 0;
        }

        cellState = null;
        similarCells = 0;

        for (int i = 0; i <= width - targetSimilarCells; i++) {
            int k = 0;
            while ((k + i) < width) {
                if (getCellState(i + k, k) == cellState) {
                    similarCells++;
                } else {
                    cellState = getCellState(i + k, k);
                    similarCells = 1;
                }
                if (cellState != CellState.EMPTY
                        && similarCells == targetSimilarCells) {
                    return cellState;
                }
                k++;
            }
            cellState = null;
            similarCells = 0;
        }

        cellState = null;
        similarCells = 0;

        for (int i = 0; i <= height - targetSimilarCells; i++) {
            int k = 0;
            while ((k + i) < height) {
                if (getCellState(k, i + k) == cellState) {
                    similarCells++;
                } else {
                    cellState = getCellState(k, i + k);
                    similarCells = 1;
                }
                if (cellState != CellState.EMPTY
                        && similarCells == targetSimilarCells) {
                    return cellState;
                }
                k++;
            }
            cellState = null;
            similarCells = 0;
        }


        cellState = null;
        similarCells = 0;

        for (int i = width-1; i >= targetSimilarCells; i--) {
            int k = 0;
            while ((i - k) >= 0 && k < height) {
                if (getCellState(i - k, k) == cellState) {
                    similarCells++;
                } else {
                    cellState = getCellState(i - k, k);
                    similarCells = 1;
                }
                if (cellState != CellState.EMPTY
                        && similarCells == targetSimilarCells) {
                    return cellState;
                }
                k++;
            }
            cellState = null;
            similarCells = 0;
        }


        cellState = null;
        similarCells = 0;

        for (int i = 0; i <= height - targetSimilarCells; i++) {
            int k = 0;
            while ((i+k) < height) {
                if (getCellState(height-1-k, i + k) == cellState) {
                    similarCells++;
                } else {
                    cellState = getCellState(height-1-k, i + k);
                    similarCells = 1;
                }
                if (cellState != CellState.EMPTY
                        && similarCells == targetSimilarCells) {
                    return cellState;
                }
                k++;
            }
            cellState = null;
            similarCells = 0;
        }
        return null;
    }
}
