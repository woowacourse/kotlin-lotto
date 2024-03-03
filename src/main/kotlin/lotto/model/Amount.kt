package lotto.model

class Amount(val money: Int) {
    companion object {
        private const val LOTTERY_TICKET_PRICE = 1000
        const val EXCEPTION_LESS_THAN_THOUSAND = "로또 구입 금액은 ${LOTTERY_TICKET_PRICE}원 이상이어야 합니다"

        private fun Int.validatePriceBound(): Int {
            require(this >= LOTTERY_TICKET_PRICE) { EXCEPTION_LESS_THAN_THOUSAND }
            return this
        }

        fun fromInput(input: String): Amount? {
            val moneyInput = input.toIntOrNull() ?: return null
            val money = moneyInput.validatePriceBound()
            return Amount(money)
        }
    }
}
