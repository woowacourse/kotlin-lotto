package lotto.generator

import lotto.domain.Lotto
import lotto.domain.LottoNumber

class LottoRandomGenerator {
    fun getRandomNumberList(): Lotto {
        return Lotto(
            (MIN_BOUND..MAX_BOUND)
                .shuffled()
                .take(NUMBER_OF_LOTTO_NUMBER).map { LottoNumber(it) }.toSet(),
        )
    }

    companion object {
        const val MAX_BOUND: Int = 45
        const val MIN_BOUND: Int = 1
        const val NUMBER_OF_LOTTO_NUMBER: Int = 6
    }
}
