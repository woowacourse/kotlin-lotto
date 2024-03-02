package model

class ManualLotteryCount(val count: Int) {
    companion object {
        private const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        private const val EXCEPTION_INSUFFICIENT_MONEY = "금액이 부족합니다"

        private fun String.toInt() = this.toIntOrNull() ?: throw IllegalArgumentException(EXCEPTION_IS_NOT_NUMBER)

        private fun Int.validatePurchaseBound(
            amount: Amount,
            lotteryTicketPrice: Int,
        ): Int {
            require(this <= amount.money / lotteryTicketPrice) { EXCEPTION_INSUFFICIENT_MONEY }
            return this
        }

        fun fromInput(
            input: String,
            amount: Amount,
            lotteryTicketPrice: Int,
        ): ManualLotteryCount {
            val count = input.toInt().validatePurchaseBound(amount, lotteryTicketPrice)
            return ManualLotteryCount(count)
        }
    }
}
