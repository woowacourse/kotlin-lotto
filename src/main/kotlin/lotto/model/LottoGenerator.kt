package lotto.model

import lotto.util.Constant

object LottoGenerator {
    private val lottos = Constant.LOTTO_NUM_RANGE.toList()

    fun generateLotto(): Lotto {
        return Lotto(
            numbers = lottos
                .shuffled()
                .take(Constant.LOTTO_LEN)
                .toSet()
        )
    }
}
