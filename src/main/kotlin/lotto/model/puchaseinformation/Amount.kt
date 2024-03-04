package lotto.model.puchaseinformation

class Amount(val money: Int) {
    init {
        require(money >= LOTTERY_TICKET_PRICE) { EXCEPTION_LESS_THAN_THOUSAND }
    }

    companion object {
        private const val LOTTERY_TICKET_PRICE = 1000
        const val EXCEPTION_LESS_THAN_THOUSAND = "로또 구입 금액은 ${LOTTERY_TICKET_PRICE}원 이상이어야 합니다"
    }
}
