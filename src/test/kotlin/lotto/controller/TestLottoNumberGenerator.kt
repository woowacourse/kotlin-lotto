package lotto.controller

import lotto.domain.LottoNumberGenerator
import lotto.domain.model.LottoNumber

class TestLottoNumberGenerator : LottoNumberGenerator {
    override fun generate(count: Int): List<List<LottoNumber>> {
        return listOf(
            listOf(1, 4, 9, 19, 24, 36).map { LottoNumber.from(it) },
            listOf(27, 29, 30, 31, 33, 40).map { LottoNumber.from(it) }
        )
    }
}
