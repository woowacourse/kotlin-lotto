package lotto.generator

import lotto.domain.Lotto
import lotto.domain.LottoNumber

class LottoManualGenerator : LottoGenerator {
    override fun generateLottoNumbers(): Lotto {
        return Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }.toSet())
    }
}
