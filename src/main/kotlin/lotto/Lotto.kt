package lotto

class Lotto(
    val numbers: List<Int>,
) {
    init {
        require(numbers.size == numbers.distinct().size) { "중복된 숫자가 존재합니다." }
    }

    companion object {
        const val PRICE = 1_000
    }
}
