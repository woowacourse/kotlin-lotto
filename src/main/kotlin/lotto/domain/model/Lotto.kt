package lotto.domain.model

import lotto.ERROR_PREFIX
import lotto.LOTTO_NUMBER_SIZE

class Lotto(val numbers: Set<LottoNumber>) {
    constructor(numbers: List<Int>) : this(numbers.map { LottoNumber(it) }.toSet())

    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) { INVALID_LOTTO_NUMBER_SIZE_MESSAGE }
    }

    private companion object {
        const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "${ERROR_PREFIX}로또 번호는 6개여야 합니다."
    }
}
