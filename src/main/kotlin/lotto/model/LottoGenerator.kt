package lotto.model

object LottoGenerator {
    private val lottos = Lotto.LOTTO_NUM_RANGE.toList()

    fun generateLotto(): Lotto {
        return Lotto(
            lottoNumber = LottoNumber(getLottoNumbers())
        )
    }

    private fun getLottoNumbers(): Set<Int> {
        return lottos
            .shuffled()
            .take(Lotto.LOTTO_LEN)
            .toSet()
    }
}
