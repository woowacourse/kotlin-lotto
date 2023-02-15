package lotto.model

import lotto.entity.Lotto

class RandomLottoGenerator : LottoGenerator {
    override fun generate(): Lotto {
        val numbers = (Lotto.MINIMUM_LOTTO_NUMBER..Lotto.MAXIMUM_LOTTO_NUMBER).toList()
        val shuffledNumbers = numbers.shuffled()
        return Lotto(shuffledNumbers.slice(Lotto.LOTTO_MINIMUM_RANGE until Lotto.LOTTO_COUNT).sorted())
    }
}
