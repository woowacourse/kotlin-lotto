package lotto.domain

class Lotto(
    vararg numbers: Int,
) {
    val numbers: Set<Int> = numbers.toSet()

    init {
        require(numbers.size == LOTTO_SIZE) { ERROR_MESSAGE_LOTTO_NEEDS_6_NUMBERS }
        require(this.numbers.size == LOTTO_SIZE) { ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_NOT_DUPLICATE }
        require(numbers.all { number: Int -> number in LOTTO_NUMBER_MIN..LOTTO_NUMBER_MAX }) {
            ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_BE_IN_1_TO_45
        }
    }

    companion object {
        const val LOTTO_PRICE = 1_000
        const val LOTTO_NUMBER_MIN = 1
        const val LOTTO_NUMBER_MAX = 45

        const val LOTTO_SIZE = 6
        private const val ERROR_MESSAGE_LOTTO_NEEDS_6_NUMBERS = "로또는 6개의 숫자를 갖고 있어야 합니다."
        private const val ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_BE_IN_1_TO_45 = "로또는 1부터 45 사이의 숫자만 가질 수 있습니다."
        private const val ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_NOT_DUPLICATE = "로또 숫자는 중복되지 않아야 합니다."
    }
}
