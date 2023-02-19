package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoNumber

class RandomLottoGenerator : LottoGenerator {
    override fun generate(): Lotto {
        val numbers = (LottoNumber.MINIMUM_LOTTO_NUMBER..LottoNumber.MAXIMUM_LOTTO_NUMBER).toList()
        val shuffledNumbers = numbers.shuffled()
        return Lotto(shuffledNumbers.slice(Lotto.LOTTO_MINIMUM_RANGE until Lotto.LOTTO_COUNT).sorted().map { LottoNumber(it) })
    }
}
