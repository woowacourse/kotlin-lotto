package lotto.domain

class Order(
    payment: Payment,
    manualQuantity: Quantity,
    manualNumbersList: List<List<Int>>,
) {
    val automaticQuantity = Quantity(payment.amount / Lotto.PRICE - manualQuantity.value)
    val manualNumbersList: List<List<LottoNumber>> =
        manualNumbersList.map { numbers: List<Int> ->
            numbers.map(LottoNumber::from)
        }

    init {
        require(payment.amount / Lotto.PRICE >= manualQuantity.value) { ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS }
        require(manualQuantity.value == manualNumbersList.size) { ERROR_MESSAGE_INSUFFICIENT_MANUAL_NUMBERS }
    }

    companion object {
        private const val ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS = "수동으로 구매할 로또의 개수는 로또의 총 개수를 초과할 수 없습니다."
        private const val ERROR_MESSAGE_INSUFFICIENT_MANUAL_NUMBERS = "수동으로 구매할 로또의 개수와 입력된 로또 번호 목록의 크기가 다릅니다."
    }

    class Quantity(val value: Int) {
        init {
            require(value >= 0) { ERROR_MESSAGE_NEGATIVE_LOTTO_QUANTITY }
        }

        companion object {
            private const val ERROR_MESSAGE_NEGATIVE_LOTTO_QUANTITY = "로또의 개수는 0보다 작을 수 없습니다."
        }
    }

    class Payment(val amount: Int) {
        init {
            require(amount >= Lotto.PRICE) { ERROR_MESSAGE_NOT_ENOUGH_MONEY }
        }

        companion object {
            private const val ERROR_MESSAGE_NOT_ENOUGH_MONEY = "구입 금액은 적어도 한 개의 로또를 구매할 수 있어야 합니다."
        }
    }
}
