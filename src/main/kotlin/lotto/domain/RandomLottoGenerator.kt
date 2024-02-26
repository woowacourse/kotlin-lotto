package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.Lotto.Companion.toLotto

class RandomLottoGenerator : LottoGenerator {
    private val lottoNumbers = (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER).toList()
    override fun make(): Lotto {
        return lottoNumbers.shuffled().take(LOTTO_SIZE).toLotto()
    }

    companion object {
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        const val LOTTO_SIZE = 6
    }
}
