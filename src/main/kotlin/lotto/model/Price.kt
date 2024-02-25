package lotto.model

import lotto.exception.ErrorCode.INVALID_PURCHASE_AMOUNT
import lotto.exception.ErrorCode.PURCHASE_AMOUNT_NOT_NATURAL_NUMBER
import lotto.exception.ExceptionsHandler.handleValidation

class Price(private val money: Int) {
    fun getNumberOfLottoTickets(): Int = money / LottoMachine.MIN_PRICE

    companion object {
        private const val MIN_PRICE = 1_000

        fun from(strMoney: String): Price {
            handleValidation(PURCHASE_AMOUNT_NOT_NATURAL_NUMBER) { strMoney.toIntOrNull() != null }
            handleValidation(INVALID_PURCHASE_AMOUNT) {
                strMoney.toIntOrNull()?.let { it >= MIN_PRICE } == true
            }
            return Price(strMoney.toInt())
        }
    }
}
