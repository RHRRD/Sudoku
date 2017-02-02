package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileScanner {

    public FieldElement[][] scanAndSetFieldFromFile(String name) {
        File file = new File("fields/" + name);
        FieldElement[][] field;
        try {
            Scanner scanner = new Scanner(file);
            int length = scanner.nextInt();
            field = new FieldElement[length][length];
            while(scanner.hasNext()) {
                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field[i].length; j++) {
                        int num = scanner.nextInt();
                        field[i][j] = num == 0 ? new FieldElement(num, new ArrayList<Integer>(Field.numbers)) :
                                new FieldElement(num, Collections.<Integer>emptyList());
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
