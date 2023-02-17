package lotto.model

import lotto.entity.Lotto

class RandomLottoGenerator : LottoGenerator {
    private val numbers = (Lotto.MINIMUM_LOTTO_NUMBER..Lotto.MAXIMUM_LOTTO_NUMBER).toList()

    override fun generate(): Lotto {
        val shuffledNumbers = numbers.shuffled()
        return Lotto(shuffledNumbers.take(Lotto.LOTTO_COUNT).sorted())
    }
}
