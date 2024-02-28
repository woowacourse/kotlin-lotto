package lotto.model

import lotto.exception.ErrorCode.MANUAL_PURCHASE_COUNT_TOO_LARGE
import lotto.exception.ExceptionsHandler.handleValidation

class Kiosk(
    deposit: Deposit = Deposit(),
    private val record: Record = Record(),
) {
    var deposit = deposit
        private set

    fun addDeposit(money: Money) {
        deposit += money.value
    }

    fun useDepositForLottoTickets() {
        deposit -= getNumberOfLottoTickets() * MIN_PRICE
    }

    fun recordPurchase(money: Money) {
        record.value.add(money)
    }

    fun refundLastPurchase() {
        if (record.value.isNotEmpty()) {
            deposit -= record.value.removeLast().value
        }
    }

    fun createLottoBundle(
        manualPurchaseLottos: ManualPurchaseLottos,
        randomLottoCount: Int,
    ): LottoBundle {
        return LottoMachine.createLottoBundle(manualPurchaseLottos, randomLottoCount)
    }

    fun getRandomLottoCount(lottoManualPurchaseCount: LottoManualPurchaseCount): Int {
        val randomLottoCount = getNumberOfLottoTickets() - lottoManualPurchaseCount.count
        handleValidation(MANUAL_PURCHASE_COUNT_TOO_LARGE) { randomLottoCount >= ZERO }
        return randomLottoCount
    }

    fun getNumberOfLottoTickets(): Int = deposit.money.value / MIN_PRICE

    companion object {
        private const val MIN_PRICE = 1_000
        private const val ZERO = 0
    }
}
