package sudoku

import java.util.*

class FieldUtils {

    companion object {

        fun isCorrect(field: Field): Boolean {
            var num = ArrayList<Int>()
            for (i in 0..field.fields.size - 1) {
                for (j in 0..field.fields[i].size - 1) {
                    if (num.contains(field.fields[i][j].number) && field.fields[i][j].number != 0) {
                        return false
                    }
                    num.add(field.fields[i][j].number)
                }
                num = ArrayList<Int>()
            }
            for (i in 0..field.fields.size - 1) {
                for (j in 0..field.fields[i].size - 1) {
                    if (num.contains(field.fields[j][i].number) && field.fields[j][i].number != 0) {
                        return false
                    }
                    num.add(field.fields[j][i].number)
                }
                num = ArrayList<Int>()
            }
            for (i in 0..field.fields.size / 3 - 1) {
                for (j in 0..field.fields[i].size / 3 - 1) {
                    for (k in (i * 3)..(i * 3 + field.fields.size / 3 - 1)) {
                        for (l in (j * 3)..(j * 3 + field.fields[k].size / 3 - 1)) {
                            if (num.contains(field.fields[k][l].number) && field.fields[k][l].number != 0) {
                                return false
                            }
                            num.add(field.fields[k][l].number)
                        }
                    }
                    num = ArrayList<Int>()
                }
            }
            return true
        }

        fun evaluateStringsAndColumns(field: Field): Unit {
            for (i in 0..field.fields.size - 1) {
                for (j in 0..field.fields[i].size - 1) {
                    if (field.fields[i][j].number == 0) {
                        for (k in 0..field.fields.size - 1) {
                            if (field.fields[i][k].number != 0) {
                                field.fields[i][j].listOfNumber.remove(field.fields[i][k].number)
                            }
                        }
                        for (k in 0..field.fields[i].size - 1) {
                            if (field.fields[k][j].number != 0) {
                                field.fields[i][j].listOfNumber.remove(field.fields[k][j].number)
                            }
                        }
                        for (k in ((i / 3) * 3)..((i / 3) * 3 + 3 - 1)) {
                            for (l in ((j / 3) * 3)..((j / 3) * 3 + 3 - 1)) {
                                if (field.fields[k][l].number != 0) {
                                    field.fields[i][j].listOfNumber.remove(field.fields[k][l].number)
                                }
                            }
                        }
                    }
                }
            }
        }

        fun findPoint(field: Field): Point {
            for (i in 0..field.fields.size - 1) {
                for (j in 0..field.fields[i].size - 1) {
                    if (field.fields[i][j].number == 0 && field.fields[i][j].listOfNumber.isNotEmpty()) {
                        return Point(i, j, field.fields[i][j].number)
                    }
                }
            }
            return Point(-1, -1, -1)
        }

        fun finishAll(field: Field): Boolean {
            for (i in 0..field.fields.size - 1) {
                for (j in 0..field.fields[i].size - 1) {
                    if (!finishField(field.fields[i][j])) {
                        return false
                    }
                }
            }
            return true
        }

        private fun finishField(fieldElement: FieldElement): Boolean {
            return fieldElement.listOfNumber.isEmpty() && fieldElement.number != 0
        }

        @JvmStatic
        fun print(field: Field): Unit {
            println("===================")
            for (i in 0..field.fields.size - 1) {
                for (j in 0..field.fields[i].size - 1) {
                    if (j == field.fields[i].size - 1) {
                        print(field.fields[i][j].number)
                    } else {
                        print(field.fields[i][j].number.toString() + " ")
                        if (j % 3 == 2) {
                            print(" ")
                        }
                    }
                }
                println()
                if (i % 3 == 2 && i != field.fields.size - 1) {
                    println()
                }
            }
            println("===================")
        }

        fun evaluateNumbers(field: Field): Int {
            var diff = 0
            for (i in 0..field.fields.size - 1) {
                for (j in 0..field.fields[i].size - 1) {
                    val temp = field.fields[i][j].number
                    field.fields[i][j] = FieldElement.Companion.evaluateFieldElement(field.fields[i][j])
                    if (field.fields[i][j].number != temp) {
                        diff++
                    }
                }
            }
            return diff
        }

    }

}