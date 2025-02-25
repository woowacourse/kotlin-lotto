package lotto.service

import lotto.domain.LottoNumber

class LottoGenerator : LottoNumberGenerator {
    override fun generate(): List<LottoNumber> {
        return (MIN_RANGE..MAX_RANGE)
            .shuffled()
            .take(LOTTO_SIZE)
            .sorted()
            .map { LottoNumber(it) }
    }

    companion object {
        const val MIN_RANGE = 1
        const val MAX_RANGE = 45
        const val LOTTO_SIZE = 6
    }
}
