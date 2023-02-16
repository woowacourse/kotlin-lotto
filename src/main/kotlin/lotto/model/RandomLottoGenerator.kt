package lotto.model

import lotto.entity.Lotto

class RandomLottoGenerator : LottoGenerator {
    private val numbers = (Lotto.MINIMUM_LOTTO_NUMBER..Lotto.MAXIMUM_LOTTO_NUMBER).toList()

    override fun generate(): Lotto {
        val shuffledNumbers = numbers.shuffled()
        return Lotto(shuffledNumbers.slice(Lotto.LOTTO_MINIMUM_RANGE until Lotto.LOTTO_COUNT).sorted())
    }
}
