package domain

import domain.model.lotto.LottoNumber

class ManualLottoNumbersGenerator {

    fun generate(InputNumbers: List<Int>): Set<LottoNumber> = InputNumbers.map { number ->
        LottoNumber.from(number)
    }.toSet()
}
