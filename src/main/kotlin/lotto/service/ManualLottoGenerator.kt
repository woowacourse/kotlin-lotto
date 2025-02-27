package lotto.service

import lotto.domain.LottoNumber

class ManualLottoGenerator(val numbers: List<Int>) : LottoNumberGenerator {
    override fun generate(): List<LottoNumber> {
        return numbers.map { LottoNumber(it) }
    }
}
