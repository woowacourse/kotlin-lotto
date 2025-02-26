package lotto.generator

import lotto.domain.Lotto
import lotto.domain.LottoNumber

class LottoManualGenerator(private val input: String) : LottoGenerator {
    override fun generateLottoNumbers(): Lotto {
        return Lotto(input.split(",").map { LottoNumber(it.toInt()) }.toSet())
    }
}
