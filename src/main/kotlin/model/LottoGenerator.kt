package model

import util.Constant

object LottoGenerator {
    fun generateLotto(): Lotto {
        return Lotto(
            (Constant.LOTTO_START_RANGE..Constant.LOTTO_END_RANGE)
                .shuffled()
                .take(Constant.LOTTO_SIZE)
                .sorted(),
        )
    }
}
