package domain.service

import domain.model.Lotto

interface LottoGenerator {
    fun generate(quickPickLottoAmount: Int): List<Lotto>

    companion object {
        const val STANDARD_AMOUNT_UNIT = 1000
    }
}
