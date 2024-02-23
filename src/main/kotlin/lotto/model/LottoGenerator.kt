package lotto.model

import lotto.util.LottoRule

object LottoGenerator {
    private val lottos = LottoRule.LOTTO_NUM_RANGE.toList()

    fun generateLotto(): Lotto {
        return Lotto(
            numbers = lottos
                .shuffled()
                .take(LottoRule.LOTTO_LEN)
                .toSet()
        )
    }
}
