package sudoku;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class FieldElement {

    private int number;
    private List<Integer> listOfNumber;


    public static FieldElement evaluateFieldElement(FieldElement fieldElement) {
        FieldElement newFieldElement = new FieldElement();
        if (fieldElement.getNumber() != 0) {
            newFieldElement.setNumber(fieldElement.getNumber());
            newFieldElement.setListOfNumber(Collections.<Integer>emptyList());
            return newFieldElement;
        }
        if (fieldElement.getListOfNumber().size() == 1) {
            newFieldElement.setNumber(fieldElement.getListOfNumber().get(0));
            newFieldElement.setListOfNumber(Collections.<Integer>emptyList());
            return newFieldElement;
        }
        return fieldElement;
    }

}
