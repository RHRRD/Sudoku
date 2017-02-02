package sudoku;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class Field {

    public final static List numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    private FieldElement[][] fields;
    private Field prevField;
    private Point point;

    public static FieldElement[][] createField(int[][] field) {
        FieldElement[][] newField = new FieldElement[field.length][field[0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                newField[i][j] = field[i][j] == 0 ? new FieldElement(0, new ArrayList<Integer>(numbers)) :
                        new FieldElement(field[i][j], Collections.<Integer>emptyList());
            }
        }
        return newField;
    }

    public FieldElement[][] copyFields() {
        FieldElement[][] array = new FieldElement[fields.length][fields[0].length];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                array[i][j] = new FieldElement(fields[i][j].getNumber(), new ArrayList(fields[i][j].getListOfNumber()));
            }
        }
        return array;
    }

    public Field pop() {
        Point point = prevField.getPoint();
        prevField.fields[point.getX()][point.getY()].getListOfNumber().remove((Object)point.getNumber());
        return prevField;
    }

}
