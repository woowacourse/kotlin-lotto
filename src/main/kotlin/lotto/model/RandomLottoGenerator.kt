package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoNumber

class RandomLottoGenerator : LottoGenerator {
    override fun generate(): Lotto {
        val shuffledNumbers = LottoNumber.lottoNumberCache.shuffled()
        val takenNumbers = shuffledNumbers.take(Lotto.LOTTO_NUMBER_COUNT)
        val unorderedNumbers = takenNumbers.toSet()
        return Lotto(unorderedNumbers)
    }
}
