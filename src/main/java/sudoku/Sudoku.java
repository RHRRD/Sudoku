package sudoku;

public class Sudoku {

    private static Integer[][] field_diff = new Integer[][]{
            {8, 0, 0,  0, 0, 0,  0, 0, 0},
            {0, 0, 3,  6, 0, 0,  0, 0, 0},
            {0, 7, 0,  0, 9, 0,  2, 0, 0},

            {0, 5, 0,  0, 0, 7,  0, 0, 0},
            {0, 0, 0,  0, 4, 5,  7, 0, 0},
            {0, 0, 0,  1, 0, 0,  0, 3, 0},

            {0, 0, 1,  0, 0, 0,  0, 6, 8},
            {0, 0, 8,  5, 0, 0,  0, 1, 0},
            {0, 9, 0,  0, 0, 0,  4, 0, 0},
    };

    public static void main(String[] args) {
        FieldElement[][] mainField;// = Field.Companion.createField(field_diff);

        mainField = new FileScanner().scanAndSetFieldFromFile(args[0]);
        if(mainField == null) {
            return;
        }
        Field field = new Field(mainField, null, null);
        System.out.println("Input field:");
        FieldUtils.print(field);

        Sudoku sudoku = new Sudoku();
        sudoku.execute(field);
    }

    public void execute(Field field) {
        int lengthOfStack = 0;
        int y = 0;
        int diff = 0;
        while (!FieldUtils.Companion.finishAll(field) && y < 2000) {
            FieldUtils.Companion.evaluateStringsAndColumns(field);
            diff += FieldUtils.Companion.evaluateNumbers(field);
            if (!FieldUtils.Companion.isCorrect(field)) {
                field = field.pop();
                lengthOfStack--;
            }
            if (diff == 0 && FieldUtils.Companion.isCorrect(field)) {
                Point point = FieldUtils.Companion.findPoint(field);
                if (FieldUtils.Companion.finishAll(field)) {
                    break;
                }
                if (point.getNumber() == -1) {
                    field = field.pop();
                    lengthOfStack--;
                } else {
                    int newNumber = field.getFields()[point.getX()][point.getY()].getListOfNumber().iterator().next();
                    Field newOldField = new Field(field.copyFields(), field.getPrevField(), new Point(point.getX(), point.getY(), newNumber));
                    FieldElement[][] newFields = newOldField.copyFields();
                    newFields[point.getX()][point.getY()].setNumber(newNumber);
                    newFields[point.getX()][point.getY()].getListOfNumber().remove((Object)newNumber);
                    field = new Field(newFields, newOldField, null);
                    lengthOfStack++;
                }
//                System.out.println("Steps: " + y + "; Point: " + point.i + " " + point.j + " " + field.field[point.i][point.j].getNumber());
//                System.out.println("Length: " + lengthOfStack);
//                FieldUtils.print(field);
            }
            diff = 0;
            y++;
//            System.out.println("Steps: " + y);
//            FieldUtils.print(field);
        }
        System.out.println("Output field:");
        FieldUtils.print(field);
        System.out.println("Steps: " + y);
        System.out.println("Field is correct: " + FieldUtils.Companion.isCorrect(field));
    }

}
