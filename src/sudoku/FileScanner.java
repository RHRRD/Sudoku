package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileScanner {

    public FieldElement[][] scanAndSetFieldFromFile(String name, int length) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(name).getFile());
        FieldElement[][] field = new FieldElement[length][length];
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field[i].length; j++) {
                        field[i][j] = new FieldElement();
                        field[i][j].setNumber(scanner.nextInt());
                        if (field[i][j].getNumber() == 0) {
                            field[i][j].setListOfNumber(new ArrayList<>(Field.numbers));
                        } else {
                            field[i][j].setListOfNumber(Collections.emptyList());
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File with name: \"" + name + "\" not found.");
            return null;
        } catch (NoSuchElementException ex) {
            System.out.println("Error in file.");
            return null;
        }
        return field;
    }

}
