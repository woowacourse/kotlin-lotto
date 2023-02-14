package lotto

class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.size == 6)
        require(numbers.all { it in 1..45 })
        require(numbers.distinct().size == 6)
    }
}