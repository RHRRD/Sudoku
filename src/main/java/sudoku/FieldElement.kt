package sudoku

import java.util.Collections.emptyList

data class FieldElement(var number: Int, var listOfNumber: MutableCollection<Int>) {

    companion object {

        public fun evaluateFieldElement(fieldElement: FieldElement): FieldElement {
            if (fieldElement.number != 0) {
                return FieldElement(fieldElement.number, emptyList())
            }
            if (fieldElement.listOfNumber?.size == 1) {
                return FieldElement(fieldElement.listOfNumber.elementAt(0), emptyList())
            }
            return fieldElement
        }

    }

}