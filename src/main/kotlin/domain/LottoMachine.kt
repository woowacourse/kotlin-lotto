package domain

import common.MAXIMUM_LOTTO_RANGE
import common.MINIMUM_LOTTO_RANGE

interface LottoMachine {
    fun create(start: Int = MINIMUM_LOTTO_RANGE, end: Int = MAXIMUM_LOTTO_RANGE): Lotto
}
