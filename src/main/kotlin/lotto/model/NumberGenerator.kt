package lotto.model

import lotto.util.LottoConstants

class NumberGenerator {
    fun generateLottoNumbers(): List<Int> {
        return (LottoConstants.START_RANGE..LottoConstants.END_RANGE).map { it }
            .shuffled()
            .take(LottoConstants.SIZE)
            .sorted()
    }
}
