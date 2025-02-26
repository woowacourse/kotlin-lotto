package lotto.domain.service

import lotto.domain.model.LottoNumber

class FixedLottoNumbersGenerator(private val numbers: List<Int>) : LottoNumbersGenerator {
    override fun generate(): Set<LottoNumber> {
        return numbers.sorted().map { LottoNumber.from(it) }.toSet()
    }
}
