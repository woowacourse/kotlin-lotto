package lotto.model

class LottoMachine(
    private val money: Money
) {
    init {
        require(money >= Money(LOTTO_PRICE)) { "구입금액은 ${money.amount}보다 큰 ${LOTTO_PRICE}원 이상이어야 합니다." }
    }

    fun countTicket(): Int {
        val numberOfTicket = money.amount / LOTTO_PRICE
        return numberOfTicket.toInt()
    }

    fun calculateMargin(prize: Money): Margin {
        return Margin(prize.amount * LOTTO_PRICE / money.amount / LOTTO_PRICE.toDouble())
    }

    companion object {
        private const val LOTTO_PRICE = 1000L
    }
}
