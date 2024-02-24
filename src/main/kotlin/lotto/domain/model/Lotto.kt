package lotto.domain.model

class Lotto(numbers: List<LottoNumber>) {
    val numbers: List<LottoNumber> = numbers.sortedBy { it.value }

    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) { NUMBER_SIZE_EXCEPTION_MESSAGE }
        require(numbers.toSet().size == LOTTO_NUMBER_SIZE) { DUPLICATED_NUMBER_EXCEPTION_MESSAGE }
    }

    companion object {
        fun List<Int>.toLotto(): Lotto =
            this.take(LOTTO_NUMBER_SIZE).map { LottoNumber(it) }.run { Lotto(this) }

        private const val LOTTO_NUMBER_SIZE = 6
        private const val NUMBER_SIZE_EXCEPTION_MESSAGE = "로또 번호는 ${LOTTO_NUMBER_SIZE}개여야 합니다."
        private const val DUPLICATED_NUMBER_EXCEPTION_MESSAGE = "로또 번호끼리는 중복되면 안됩니다."
    }
}
