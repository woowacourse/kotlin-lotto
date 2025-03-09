package lotto.domain

import lotto.generator.LottoManualGenerator
import lotto.generator.LottoRandomGenerator

class LottoMachine {
    var totalLottoCount = 0
        private set
    private var manualLottoCount = 0

    fun createManualLottoTicket(input: Set<Int>): Lotto {
        val manualLottoTicket = LottoManualGenerator(input).generateLottoNumbers()
        return manualLottoTicket
    }

    fun createTotalLottoTicket(manualLottoTickets: List<Lotto>): List<Lotto> {
        val lottoTickets: MutableList<Lotto> = mutableListOf()
        lottoTickets += manualLottoTickets
        lottoTickets += createAutoLottoTickets()
        return lottoTickets
    }

    private fun createAutoLottoTickets(): List<Lotto> {
        val autoLottoCount = totalLottoCount - manualLottoCount
        val lottoTickets: MutableList<Lotto> = mutableListOf()
        repeat(autoLottoCount) {
            lottoTickets.add(createAutoLottoTicket())
        }
        return lottoTickets
    }

    private fun createAutoLottoTicket(): Lotto {
        return LottoRandomGenerator().generateLottoNumbers()
    }

    fun validPurchaseAmount(inputPurchaseAmount: Int): Int {
        if (inputPurchaseAmount < LOTTO_TICKET_PRICE || inputPurchaseAmount % LOTTO_TICKET_PRICE != 0) {
            throw IllegalArgumentException(ERROR_NOT_INVALID_PURCHASE_AMOUNT)
        }
        totalLottoCount = inputPurchaseAmount / LOTTO_TICKET_PRICE
        return inputPurchaseAmount
    }

    fun validManualLottoCount(inputManualLottoCount: Int): Int? {
        if (totalLottoCount < inputManualLottoCount) {
            return null
        }
        manualLottoCount = inputManualLottoCount
        return manualLottoCount
    }

    companion object {
        const val LOTTO_TICKET_PRICE = 1_000
        const val ERROR_NOT_INVALID_PURCHASE_AMOUNT = "[ERROR] 구입 금액이 올바르지 않습니다. 다시 입력해주세요."
    }
}
