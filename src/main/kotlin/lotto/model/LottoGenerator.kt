package lotto.model

object LottoGenerator {
    private val lottos = Lotto.LOTTO_NUM_RANGE.map { LottoNumber(it) }

    fun generateLotto(): Lotto {
        return Lotto(
            lottoNumbers = LottoNumbers(getLottoNumbers())
        )
    }

    private fun getLottoNumbers(): List<LottoNumber> {
        return lottos
            .shuffled()
            .take(Lotto.LOTTO_LEN)
    }
}
