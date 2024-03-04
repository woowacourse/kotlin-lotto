package lotto.model

import lotto.util.Constant

class AutomaticLotto : LottoGenerator {
    override fun generateLotto(): Lotto {
        return Lotto(
            (Constant.MIN_LOTTO_NUMBER_RANGE..Constant.MAX_LOTTO_NUMBER_RANGE)
                .shuffled()
                .take(Constant.LOTTO_SIZE)
                .sorted()
                .map { LottoNumber(it) },
        )
    }
}
