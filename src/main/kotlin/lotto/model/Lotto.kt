package lotto.model

class Lotto(private val numbers: Set<Int>) {
    init {
        require(numbers.size == 6)
        require(numbers.all{ it in (1..45)})
    }

    fun getNumbers(): Set<Int> {
        return numbers
    }

}