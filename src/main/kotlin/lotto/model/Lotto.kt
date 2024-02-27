package lotto.model

data class Lotto(val lottoNumbers: LottoNumbers) {
    init {
        require(lottoNumbers.numbers.size == LOTTO_SIZE && lottoNumbers.numbers.distinct().size == LOTTO_SIZE) { LOTTO_SIZE_ERROR_MESSAGE }
    }

    companion object {
        fun of(vararg numbers: Int): Lotto {
            return Lotto(
                LottoNumbers(
                    numbers.map(LottoNumber::of),
                ),
            )
        }

        private const val LOTTO_SIZE_ERROR_MESSAGE = "로또의 숫자들은 중복되지 않는 6개입니다."
        const val LOTTO_SIZE = 6
        const val PRICE_PER_LOTTO = 1000
    }
}
