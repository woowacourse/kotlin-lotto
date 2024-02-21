package lotto.model

import lotto.util.Constant

class LottoGenerator {
    fun generateLotto(): Lotto {
        return Lotto(
            numbers = lottos
                .shuffled()
                .take(Constant.LOTTO_LEN)
                .toSet()
        )
    }

    companion object {
        private val lottos = Constant.LOTTO_NUM_RANGE.toList()
    }
}