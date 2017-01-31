package sudoku;

public class Sudoku {

    private static int[][] field_diff = new int[][]{
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
        Field field = new Field();
        FieldElement[][] mainField;// = Field.createField(field3);

        mainField = new FileScanner().scanAndSetFieldFromFile(args[0]);
        if(mainField == null) {
            return;
        }
        field.setFields(mainField);
        System.out.println("Input field:");
        FieldUtils.print(field);

        Sudoku sudoku = new Sudoku();
        sudoku.execute(field);
    }

    public void execute(Field field) {
        int lengthOfStack = 0;
        int y = 0;
        int diff = 0;
        while (!FieldUtils.finishAll(field) && y < 2000) {
            FieldUtils.evaluateStringsAndColumns(field);
            diff += FieldUtils.evaluateNumbers(field);
//            test();
            if (!FieldUtils.isCorrect(field)) {
                field = field.pop();
                lengthOfStack--;
            }
            if (diff == 0 && FieldUtils.isCorrect(field)) {
                Point point = FieldUtils.findPoint(field);
                if (FieldUtils.finishAll(field)) {
                    break;
                }
                if (point.getNumber() == -1) {
                    field = field.pop();
                    lengthOfStack--;
                } else {
                    int newNumber = field.getFields()[point.getX()][point.getY()].getListOfNumber().get(0);
                    Field newOldField = new Field();
                    newOldField.setPoint(new Point(point.getX(), point.getY(), newNumber));
                    newOldField.setPrevField(field.getPrevField());
                    newOldField.setFields(field.copyFields());
                    field = new Field();
                    field.setPrevField(newOldField);
                    FieldElement[][] newFields = newOldField.copyFields();
                    newFields[point.getX()][point.getY()].setNumber(newNumber);
                    newFields[point.getX()][point.getY()].getListOfNumber().remove((Object)newNumber);
                    field.setFields(newFields);
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
        System.out.println("Field is correct: " + FieldUtils.isCorrect(field));
    }

}
