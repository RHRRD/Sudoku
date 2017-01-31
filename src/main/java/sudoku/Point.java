package sudoku;

import lombok.Data;

@Data
public class Point {

    private int x;
    private int y;
    private int number;

    public Point(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }

}
