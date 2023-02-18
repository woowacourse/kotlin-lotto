package domain

import common.MAXIMUM_LOTTO_RANGE
import common.MINIMUM_LOTTO_RANGE

class LottoSeller(private val numberGenerator: NumberGenerator = RandomNumberGenerator()) {
    fun sellLotto(): Lotto {
        return Lotto(numberGenerator.generateSixNumber(MINIMUM_LOTTO_RANGE, MAXIMUM_LOTTO_RANGE))
    }

    fun sellLottos(count: Int): Ticket {
        return Ticket(List(count) { Lotto(numberGenerator.generateSixNumber(MINIMUM_LOTTO_RANGE, MAXIMUM_LOTTO_RANGE)) })
    }
}
