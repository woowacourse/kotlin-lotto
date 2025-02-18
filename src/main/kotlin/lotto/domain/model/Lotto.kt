package lotto.domain.model

class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) { INVALID_LOTTO_NUMBER_SIZE_MESSAGE }
    }

    private companion object {
        const val LOTTO_NUMBER_SIZE = 6
        const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "로또 번호는 6개여야 합니다."
    }
}
