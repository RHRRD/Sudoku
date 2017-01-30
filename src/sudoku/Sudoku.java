package sudoku;

public class Sudoku {

    private static int[][] field0 = new int[][]{
            {2, 5, 6, 0, 1, 4},
            {3, 0, 0, 0, 0, 2},
            {0, 0, 3, 1, 0, 0},
            {1, 0, 2, 0, 0, 0},
            {4, 0, 0, 0, 6, 1},
            {0, 2, 1, 5, 4, 0}
    };

    private static int[][] field1 = new int[][]{
            {0, 0, 9,  3, 0, 1,  6, 0, 0},
            {0, 5, 0,  0, 8, 0,  3, 9, 0},
            {3, 1, 8,  0, 0, 0,  7, 0, 5},

            {2, 0, 0,  8, 0, 3,  0, 0, 4},
            {0, 8, 0,  0, 4, 0,  0, 6, 0},
            {5, 0, 0,  6, 0, 9,  0, 0, 3},

            {1, 0, 2,  0, 0, 0,  4, 3, 7},
            {0, 7, 5,  0, 3, 0,  0, 1, 0},
            {0, 0, 6,  1, 0, 2,  5, 0, 0}
    };

    private static int[][] field2 = new int[][]{
            {0, 6, 2,  0, 0, 0,  0, 8, 4},
            {0, 0, 0,  0, 9, 6,  5, 0, 0},
            {5, 9, 0,  0, 8, 2,  0, 1, 7},

            {0, 4, 0,  0, 1, 9,  3, 6, 0},
            {6, 0, 0,  0, 0, 0,  4, 9, 0},
            {0, 3, 0,  6, 0, 8,  0, 5, 1},

            {0, 0, 0,  0, 0, 0,  0, 4, 9},
            {0, 5, 0,  0, 0, 0,  0, 0, 6},
            {8, 0, 7,  0, 6, 0,  1, 0, 5},
    };

    private static int[][] field3 = new int[][]{
            {2, 0, 4,  9, 5, 0,  0, 0, 3},
            {6, 0, 8,  1, 7, 3,  0, 0, 5},
            {1, 5, 3,  0, 0, 0,  0, 7, 9},

            {0, 0, 0,  0, 1, 2,  0, 0, 4},
            {9, 0, 0,  0, 8, 5,  0, 0, 0},
            {3, 4, 2,  0, 0, 7,  0, 0, 8},

            {0, 0, 0,  5, 6, 0,  0, 0, 0},
            {0, 0, 0,  7, 0, 9,  0, 5, 0},
            {8, 0, 0,  0, 0, 1,  0, 0, 0},
    };

    private static int[][] field4 = new int[][]{
            {0, 0, 0,  1, 0, 0,  0, 0, 0},
            {0, 5, 0,  0, 7, 3,  0, 9, 0},
            {7, 9, 3,  6, 0, 0,  0, 0, 0},

            {0, 0, 0,  0, 0, 0,  0, 3, 6},
            {5, 0, 0,  3, 6, 7,  0, 0, 0},
            {3, 0, 2,  4, 0, 0,  0, 0, 7},

            {6, 0, 8,  0, 0, 4,  0, 0, 0},
            {0, 0, 0,  0, 3, 6,  0, 0, 8},
            {0, 7, 0,  0, 8, 0,  0, 6, 3}
    };

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

    private static int[][] field_small = new int[][]{
            {0, 5, 4},
            {1, 0, 3},
            {0, 2, 0}
    };


    public static void main(String[] args) {
        Field field = new Field();
        FieldElement[][] mainField = Field.createField(field3);

        field.setFields(mainField);
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
        System.out.println("Steps: " + y);
        FieldUtils.print(field);
        System.out.println(FieldUtils.isCorrect(field));
    }

}
