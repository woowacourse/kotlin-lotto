package lotto.generator

import lotto.domain.Lotto

fun interface LottoGenerator {
    fun generateLottoNumbers(): Lotto
}
