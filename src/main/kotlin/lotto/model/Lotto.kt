package lotto.model

class Lotto(
    val numbers: List<LottoNumber>,
) {
    init {
        require(numbers.size == numbers.distinct().size) { ERROR_DUPLICATED_LOTTO_NUMBERS }
        require(numbers.size == LOTTO_NUMBERS_COUNT) { ERROR_LOTTO_NUMBERS_SIZE }
    }

    fun contains(lottoNumber: LottoNumber): Boolean = numbers.contains(lottoNumber)

    companion object {
        private const val ERROR_DUPLICATED_LOTTO_NUMBERS = "중복된 숫자가 존재합니다."
        private const val ERROR_LOTTO_NUMBERS_SIZE = "로또 번호는 6개입니다."
        const val LOTTO_NUMBERS_COUNT = 6
    }
}
