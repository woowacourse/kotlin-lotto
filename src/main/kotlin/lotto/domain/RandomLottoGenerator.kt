package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber

class RandomLottoGenerator : LottoGenerator {
    override fun make(): Lotto {
        val lottoNumbers = (LottoNumber.LOTTO_NUMBER_RANGE).toList()
        return Lotto.from(lottoNumbers.shuffled().take(LOTTO_SIZE))
    }

    companion object {
        const val LOTTO_SIZE = 6
    }
}
