package model

data class LotteryPurchasePattern(val manualLottoCount: Int, val autoLottoCount: Int) {
    companion object {
        private const val LOTTERY_TICKET_PRICE = 1000
        const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        const val EXCEPTION_INSUFFICIENT_FUNDS = "금액보다 구매하려는 갯수가 더 많습니다"
        const val EXCEPTION_IS_NOT_POSITIVE = "0 이상의 수를 입력하셔야 합니다"

        fun ofManual(
            amount: Amount,
            manualInput: String,
        ): LotteryPurchasePattern {
            val availableTicketCount = amount.money / LOTTERY_TICKET_PRICE

            return manualInput.toInt().validatePositive().validateFund(amount.money).run {
                LotteryPurchasePattern(this, availableTicketCount - this)
            }
        }

        private fun String.toInt(): Int {
            return this.toIntOrNull() ?: throw IllegalArgumentException(EXCEPTION_IS_NOT_NUMBER)
        }

        private fun Int.validatePositive(): Int {
            require(this >= 0) { EXCEPTION_IS_NOT_POSITIVE }
            return this
        }

        private fun Int.validateFund(money: Int): Int {
            require(money / LOTTERY_TICKET_PRICE >= this) { EXCEPTION_INSUFFICIENT_FUNDS }
            return this
        }
    }
}
