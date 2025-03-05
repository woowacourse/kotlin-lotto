package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.Lotto.Companion.LOTTO_NUMBER_SIZE

class RandomLottoMachine : LottoMachine {
    override fun generate(numbers: List<Int>): Lotto {
        val randomNumbers = numbers.shuffled()
        return Lotto(randomNumbers.take(LOTTO_NUMBER_SIZE))
    }
}
