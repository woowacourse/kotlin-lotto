package lotto.model


class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.isValidSize() && numbers.isNotDuplicate() && numbers.isInRange())
    }

    private fun List<Int>.isValidSize() = size == 6

    private fun List<Int>.isNotDuplicate() = distinct().size == 6

    private fun List<Int>.isInRange() = all { number -> number in 1..45 }
}
