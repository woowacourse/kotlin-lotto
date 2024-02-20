package lotto.model

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == NUMBER_COUNT) { "Error: 로또 번호의 개수는 6개이어야 합니다." }
        require(numbers.toSet().size == numbers.size) { "Error: 로또 번호는 중복될 수 없습니다." }
        require(numbers.all { it in NUMBER_RANGE }) { "Error: 로또 번호의 범위는 1~45 사이의 자연수입니다." }
    }

    companion object {
        const val NUMBER_COUNT = 6
        val NUMBER_RANGE = (1..45)
    }
}
