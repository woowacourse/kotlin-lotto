package lotto.domain.model

import lotto.domain.LottoRules

class Lotto(val numbers: Set<LottoNumber>) {
    constructor(numbers: List<Int>) : this(numbers.map { LottoNumber(it) }.toSet())

    init {
        require(numbers.size == LottoRules.LOTTO_NUMBER_SIZE.value) { INVALID_LOTTO_NUMBER_SIZE_MESSAGE }
    }

    private companion object {
        const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "로또 번호는 6개여야 하며, 중복될 수 없습니다."
    }
}
