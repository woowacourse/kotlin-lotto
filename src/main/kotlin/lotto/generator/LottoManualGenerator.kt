package lotto.generator

import lotto.domain.Lotto
import lotto.domain.LottoNumber

class LottoManualGenerator(private val input: Set<Int>) : LottoGenerator {
    override fun generateLottoNumbers(): Lotto {
        return Lotto(input.map { LottoNumber.from(it) }.toSet())
    }
}
