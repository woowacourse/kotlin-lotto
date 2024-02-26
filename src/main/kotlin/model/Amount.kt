package model

class Amount(val money: Int) {
    companion object {
        private const val LOTTERY_TICKET_PRICE = 1000
        const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        const val EXCEPTION_LESS_THAN_THOUSAND = "로또 구입 금액은 ${LOTTERY_TICKET_PRICE}원 이상이어야 합니다"

        private fun String.toInt(): Int {
            return this.toIntOrNull() ?: throw IllegalArgumentException(EXCEPTION_IS_NOT_NUMBER)
        }

        private fun Int.validatePriceBound(): Int {
            require(this >= LOTTERY_TICKET_PRICE) { EXCEPTION_LESS_THAN_THOUSAND }
            return this
        }

        fun fromInput(input: String): Amount {
            val money = input.toInt().validatePriceBound()
            return Amount(money)
        }
    }
}
