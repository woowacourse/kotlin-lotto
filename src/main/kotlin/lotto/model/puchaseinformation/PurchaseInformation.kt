package lotto.model.puchaseinformation

class PurchaseInformation(
    val amount: Int,
    val manualLotteryCount: Int = 0,
    lotteryTicketPrice: Int = 1000,
) {
    init {
        require(amount >= lotteryTicketPrice) { EXCEPTION_LESS_THAN_THOUSAND }
        require(manualLotteryCount <= amount / lotteryTicketPrice) { EXCEPTION_INSUFFICIENT_MONEY }
    }

    companion object {
        private const val LOTTERY_TICKET_PRICE = 1000
        private const val EXCEPTION_INSUFFICIENT_MONEY = "금액이 부족합니다"
        private const val EXCEPTION_LESS_THAN_THOUSAND = "로또 구입 금액은 ${LOTTERY_TICKET_PRICE}원 이상이어야 합니다\n"
    }
}
