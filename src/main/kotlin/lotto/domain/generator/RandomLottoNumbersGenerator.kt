package lotto.domain.generator

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber

class RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(): List<LottoNumber> {
        val selectedNumbers = LottoNumber.NUMBERS.keys.shuffled().take(Lotto.LOTTO_NUMBER_SIZE)
        return selectedNumbers.map { LottoNumber.from(it) }
    }
}
