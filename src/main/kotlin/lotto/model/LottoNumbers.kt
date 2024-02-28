package lotto.model

data class LottoNumbers(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUMBERS_SIZE && numbers.distinct().size == LOTTO_NUMBERS_SIZE) { LOTTO_NUMBERS_SIZE_ERROR_MESSAGE }
    }

    companion object {
        private const val LOTTO_NUMBERS_SIZE_ERROR_MESSAGE = "로또의 숫자들은 중복되지 않는 6개입니다."
        const val LOTTO_NUMBERS_SIZE = 6
    }
}
