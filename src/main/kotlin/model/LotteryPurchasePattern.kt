package model

class LotteryPurchasePattern private constructor(val manualLottoCount: Int, val autoLottoCount: Int) {
    companion object {
        private const val LOTTERY_TICKET_PRICE = 1000
        const val EXCEPTION_INSUFFICIENT_FUNDS = "금액보다 구매하려는 갯수가 더 많습니다"

        fun ofManual(
            amount: Amount,
            manualInput: String,
        ): LotteryPurchasePattern {
            val availableTicketCount = amount.money / LOTTERY_TICKET_PRICE

            return manualInput.toInt().validateFund(amount.money).run {
                LotteryPurchasePattern(this, availableTicketCount - this)
            }
        }

        private fun String.toInt(): Int {
            return this.toIntOrNull() ?: throw IllegalArgumentException(Amount.EXCEPTION_IS_NOT_NUMBER)
        }

        private fun Int.validateFund(money: Int): Int {
            require(money / LOTTERY_TICKET_PRICE >= this) { EXCEPTION_INSUFFICIENT_FUNDS }
            return this
        }
    }
}
