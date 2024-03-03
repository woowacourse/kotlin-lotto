package lotto.domain

import lotto.domain.model.Lotto

class RandomLottoGenerator : LottoGenerator {
    override fun make(minimumInclusive: Int, maximumInclusive: Int): Lotto {
        val lottoNumbers = (minimumInclusive..maximumInclusive).toList()
        return Lotto.from(lottoNumbers.shuffled().take(LOTTO_SIZE))
    }

    companion object {
        const val LOTTO_SIZE = 6
    }
}
