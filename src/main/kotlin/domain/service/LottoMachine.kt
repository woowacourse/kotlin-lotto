package domain.service

import domain.model.lotto.Lotto

fun interface LottoMachine {
    fun generate(): Lotto

    companion object {
        const val STANDARD_AMOUNT_UNIT = 1000
    }
}
