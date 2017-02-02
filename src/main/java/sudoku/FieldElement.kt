package sudoku

import java.util.Collections.emptyList

data class FieldElement(var number: Int, var listOfNumber: List<Int>) {

    companion object {

        public fun evaluateFieldElement(fieldElement: FieldElement): FieldElement {
            if (fieldElement.number != 0) {
                return FieldElement(fieldElement.number, emptyList())
            }
            if (fieldElement.listOfNumber?.size == 1) {
                return FieldElement(fieldElement.listOfNumber.get(0), emptyList())
            }
            return fieldElement
        }

    }

}