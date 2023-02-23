package lotto.view

import java.util.SortedSet

object InputView {
    fun getNumber(): Int? {
        val input = readln().trim()
        return input.toIntOrNull()
    }

    fun getNumberList(): SortedSet<Int>? {
        val input = readln().trim().split(",").toSortedSet()
        if (input.isNumbers())
            return input.mapNotNull { it.toIntOrNull() }.toSortedSet()
        return null
    }

    private fun String.isNumber() = this.chars().allMatch { Character.isDigit(it) }
    private fun SortedSet<String>.isNumbers() = !this.any { !it.trim().isNumber() || it.trim().isBlank() }
}
