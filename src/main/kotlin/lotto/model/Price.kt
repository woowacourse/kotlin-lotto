package lotto.model

import lotto.exception.ErrorCode
import lotto.exception.ErrorCode.INVALID_PURCHASE_AMOUNT
import lotto.exception.ErrorCode.PURCHASE_AMOUNT_NOT_NATURAL_NUMBER
import lotto.exception.ExceptionsHandler.handleValidation

data class Price(val money: Int = DEFAULT_MONEY) {
    fun plusPrice(strMoney: String): Price {
        handleValidation(PURCHASE_AMOUNT_NOT_NATURAL_NUMBER) { strMoney.toIntOrNull() != null }
        handleValidation(INVALID_PURCHASE_AMOUNT) {
            strMoney.toIntOrNull()?.let { it >= MIN_PRICE } == true
        }
        return copy(money = money + strMoney.toInt())
    }

    fun minusPrice(): Price = copy(money = money - getNumberOfLottoTickets() * MIN_PRICE)

    fun getRandomLottoCount(lottoManualPurchaseCount: Int): Int {
        val randomLottoCount = getNumberOfLottoTickets() - lottoManualPurchaseCount
        handleValidation(ErrorCode.MANUAL_PURCHASE_COUNT_TOO_LARGE) { randomLottoCount >= 0 }
        return randomLottoCount
    }

    fun getNumberOfLottoTickets(): Int = money / MIN_PRICE

    companion object {
        private const val DEFAULT_MONEY = 0
        private const val MIN_PRICE = 1_000
    }
}
