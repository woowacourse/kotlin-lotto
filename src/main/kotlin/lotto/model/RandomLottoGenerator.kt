package lotto.model

import lotto.entity.Lotto

class RandomLottoGenerator : LottoGenerator {
    override fun generate(): Lotto {
        val numbers = (1..45).toList()
        val shuffledNumbers = numbers.shuffled()
        return Lotto(shuffledNumbers.slice(0 until 6))
    }
}
