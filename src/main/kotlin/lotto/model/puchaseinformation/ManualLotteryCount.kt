package lotto.model.puchaseinformation

class ManualLotteryCount(val count: Int) {
    companion object {
        private const val EXCEPTION_INSUFFICIENT_MONEY = "금액이 부족합니다"

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
        ): ManualLotteryCount? {
            val countInput = input.toIntOrNull() ?: return null
            val count = countInput.validatePurchaseBound(amount, lotteryTicketPrice)

            return ManualLotteryCount(count)
        }
    }
}
