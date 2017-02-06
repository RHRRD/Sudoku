package sudoku

import java.util.*

data class Field(var fields: Array<Array<FieldElement>>, var prevField: Field?, var point: Point?) {

    fun copyFields(): Array<Array<FieldElement>> {
        val array = Array<Array<FieldElement>>(fields.size, { i -> Array<FieldElement>(fields.size, { FieldElement(0, mutableListOf()) })})
        for (i in fields.indices) {
            for (j in 0..fields[i].size - 1) {
                array[i][j] = FieldElement(fields[i][j].number, ArrayList(fields[i][j].listOfNumber))
            }
        }
        return array
    }

    fun pop(): Field {
        val point = prevField?.point
        prevField!!.fields[point!!.x][point!!.y].listOfNumber.remove(point.number as Any)
        return prevField!!
    }

    companion object {
        val numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)

        fun createField(field: Array<Array<Int>>): Array<Array<FieldElement>> {
            val newField =  Array<Array<FieldElement>>(field.size, {i -> Array<FieldElement>(field.size, { FieldElement(0, mutableListOf()) })})
            for (i in field.indices) {
                for (j in 0..field[i].size - 1) {
                    newField[i][j] = if (field[i][j] == 0)
                        FieldElement(0, ArrayList<Int>(numbers))
                    else
                        FieldElement(field[i][j], mutableListOf())
                }
            }
            return newField
        }
    }

}