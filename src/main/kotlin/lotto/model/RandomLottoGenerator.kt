package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoNumber

class RandomLottoGenerator : LottoGenerator {
    override fun generate(): Lotto {
        val shuffledNumbers = LottoNumber.lottoNumberCache.shuffled()
        return Lotto(shuffledNumbers.take(Lotto.LOTTO_NUMBER_COUNT).sortedBy { it.value }.toSet())
    }
}
