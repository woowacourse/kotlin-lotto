package lotto.model

data class Lotto(val lottoNumbers: LottoNumbers) {
    companion object {
        fun of(vararg numbers: Int): Lotto {
            return Lotto(
                LottoNumbers(
                    numbers.map(LottoNumber::of),
                ),
            )
        }

        const val PRICE_PER_LOTTO = 1000
    }
}
