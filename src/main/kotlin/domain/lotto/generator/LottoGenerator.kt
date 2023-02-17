package domain.lotto.generator

import domain.lotto.Lotto

fun interface LottoGenerator {
    fun generate(): Lotto
}
