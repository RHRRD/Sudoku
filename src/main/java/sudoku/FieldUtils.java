package sudoku;

import java.util.ArrayList;
import java.util.List;

public class FieldUtils {

    public static boolean isCorrect(Field field) {
        List num = new ArrayList();
        for (int i = 0; i < field.getFields().length; i++) {
            for (int j = 0; j < field.getFields()[i].length; j++) {
                if(num.contains(field.getFields()[i][j].getNumber()) && field.getFields()[i][j].getNumber() != 0) {
                    return false;
                }
                num.add(field.getFields()[i][j].getNumber());
            }
            num = new ArrayList<Integer>();
        }
        for (int i = 0; i < field.getFields().length; i++) {
            for (int j = 0; j < field.getFields()[i].length; j++) {
                if(num.contains(field.getFields()[j][i].getNumber()) && field.getFields()[j][i].getNumber() != 0) {
                    return false;
                }
                num.add(field.getFields()[j][i].getNumber());
            }
            num = new ArrayList<Integer>();
        }
        for (int i = 0; i < field.getFields().length / 3; i++) {
            for (int j = 0; j < field.getFields()[i].length / 3; j++) {
                for (int k = i * 3; k < i * 3 + field.getFields().length / 3; k++) {
                    for (int l = j * 3; l < j * 3 + field.getFields()[k].length / 3; l++) {
                        if(num.contains(field.getFields()[k][l].getNumber()) && field.getFields()[k][l].getNumber() != 0) {
                            return false;
                        }
                        num.add(field.getFields()[k][l].getNumber());
                    }
                }
                num = new ArrayList<Integer>();
            }
        }
        return true;
    }

    public static void evaluateStringsAndColumns(Field field) {
        for (int i = 0; i < field.getFields().length; i++) {
            for (int j = 0; j < field.getFields()[i].length; j++) {

                if (field.getFields()[i][j].getNumber() == 0) {
                    for (int k = 0; k < field.getFields().length; k++) {
                        if (field.getFields()[i][k].getNumber() != 0) {
                            field.getFields()[i][j].getListOfNumber().remove((Object) field.getFields()[i][k].getNumber());
                        }
                    }
                    for (int k = 0; k < field.getFields()[i].length; k++) {
                        if (field.getFields()[k][j].getNumber() != 0) {
                            field.getFields()[i][j].getListOfNumber().remove((Object) field.getFields()[k][j].getNumber());
                        }
                    }
                    for (int k = (i / 3) * 3; k < (i / 3) * 3 + 3; k++) {
                        for (int l = (j / 3) * 3; l < (j / 3) * 3 + 3; l++) {
                            if (field.getFields()[k][l].getNumber() != 0) {
                                field.getFields()[i][j].getListOfNumber().remove((Object) field.getFields()[k][l].getNumber());
                            }
                        }
                    }
                }

            }
        }
    }

    public static Point findPoint(Field field) {
        int num = 0, i, j = 0;
        for (i = 0; i < field.getFields().length; i++) {
            for (j = 0; j < field.getFields()[i].length; j++) {
                if (field.getFields()[i][j].getNumber() == 0 && !field.getFields()[i][j].getListOfNumber().isEmpty()) {
                    num = field.getFields()[i][j].getNumber();
                    return new Point(i, j, num);
                }
            }
        }
        return new Point(-1, -1, -1);
    }

    public static boolean finishAll(Field field) {
        for (int i = 0; i < field.getFields().length; i++) {
            for (int j = 0; j < field.getFields()[i].length; j++) {
                if (!finishField(field.getFields()[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean finishField(FieldElement fieldElement) {
        return fieldElement.getListOfNumber().isEmpty() && fieldElement.getNumber() != 0;
    }

    public static void print(Field field) {
        System.out.println("===================");
        for (int i = 0; i < field.getFields().length; i++) {
            for (int j = 0; j < field.getFields()[i].length; j++) {
                if (j == field.getFields()[i].length - 1) {
                    System.out.print(field.getFields()[i][j].getNumber());
                }
                else {
                    System.out.print(field.getFields()[i][j].getNumber() + " ");
                    if (j % 3 == 2) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
            if (i % 3 == 2 && i != field.getFields().length - 1) {
                System.out.println();
            }
        }
        System.out.println("===================");
    }

    public static int evaluateNumbers(Field field) {
        int diff = 0;
        for (int i = 0; i < field.getFields().length; i++) {
            for (int j = 0; j < field.getFields()[i].length; j++) {
                int temp = field.getFields()[i][j].getNumber();
                field.getFields()[i][j] = FieldElement.evaluateFieldElement(field.getFields()[i][j]);
                if (field.getFields()[i][j].getNumber() != temp) {
                    diff++;
                }
            }
        }
        return diff;
    }

//    public static void test(Field field) {
//        for (int i = 0; i < field.getFields().length; i++) {
//            for (int j = 0; j < field.getFields()[i].length; j++) {
//
//                for (int k = 0; k < field.getFields().length; k++) {
//                    if (field.getFields()[i][k].getNumber() != 0) {
//                        field.getFields()[i][j].getListOfNumber().remove((Object) field.getFields()[i][k].getNumber());
//                    }
//                }
//                for (int k = 0; k < field.getFields()[i].length; k++) {
//                    if (field.getFields()[k][j].getNumber() != 0) {
//                        field.getFields()[i][j].getListOfNumber().remove((Object) field.getFields()[k][j].getNumber());
//                    }
//                }
//                for (int k = (i / 3) * 3; k < (i / 3) * 3 + 3; k++) {
//                    for (int l = (j / 3) * 3; l < (j / 3) * 3 + 3; l++) {
//                        if (field.getFields()[k][l].getNumber() != 0) {
//                            field.getFields()[i][j].getListOfNumber().remove((Object) field.getFields()[k][l].getNumber());
//                        }
//                    }
//                }
//
//            }
//        }
//    }

}
