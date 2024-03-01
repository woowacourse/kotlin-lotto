package lotto.util

interface NumbersGenerator {
    fun generateNumbers(count: Int): List<Set<Int>>
}
