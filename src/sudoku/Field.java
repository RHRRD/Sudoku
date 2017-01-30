package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Field {

    public final static List numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    private FieldElement[][] fields;
    private Field prevField;
    private Point point;

    public static FieldElement[][] createField(int[][] field) {
        FieldElement[][] newField = new FieldElement[field.length][field[0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                newField[i][j] = new FieldElement();
                if (field[i][j] == 0) {
                    newField[i][j].setNumber(0);
                    newField[i][j].setListOfNumber(new ArrayList<>(numbers));
                } else {
                    newField[i][j].setNumber(field[i][j]);
                    newField[i][j].setListOfNumber(Collections.emptyList());
                }
            }
        }
        return newField;
    }

    public FieldElement[][] copyFields() {
        FieldElement[][] array = new FieldElement[fields.length][fields[0].length];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                array[i][j] = new FieldElement();
                array[i][j].setNumber(fields[i][j].getNumber());
                array[i][j].setListOfNumber(new ArrayList(fields[i][j].getListOfNumber()));
            }
        }
        return array;
    }

    public Field pop() {
        Point point = prevField.getPoint();
        prevField.fields[point.getX()][point.getY()].getListOfNumber().remove((Object)point.getNumber());
        return prevField;
    }

    public Field getPrevField() {
        return prevField;
    }

    public void setPrevField(Field prevField) {
        this.prevField = prevField;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setFields(FieldElement[][] fields) {
        this.fields = fields;
    }

    public FieldElement[][] getFields() {
        return this.fields;
    }
}
