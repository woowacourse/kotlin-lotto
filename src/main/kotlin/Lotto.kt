package lotto

class Lotto(numbers: List<Int>) {
    init {
        require(numbers.size == LOTTO_SIZE) { INVALID_LOTTO_SIZE }
        require(numbers.all { it in (1..45) }) { INVALID_LOTTO_NUMBERS }
    }

    companion object {
        const val ERROR = "[ERROR]"
        const val LOTTO_SIZE = 6
        const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 6개 입니다."
        const val INVALID_LOTTO_NUMBERS = "$ERROR 로또 번호는 1부터 45 사이입니다."
    }
}
