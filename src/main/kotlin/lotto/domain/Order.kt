package lotto.domain

class Order(
    val payment: Int,
    val manualQuantity: Int,
) {
    val automaticQuantity: Int = payment / Lotto.PRICE - manualQuantity

    init {
        require(payment >= Lotto.PRICE) { ERROR_MESSAGE_NOT_ENOUGH_MONEY }
        require(manualQuantity >= 0) { ERROR_MESSAGE_NOT_ENOUGH_MANUAL_LOTTOS }
        require(payment >= manualQuantity * Lotto.PRICE) { ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS }
    }

    companion object {
        private const val ERROR_MESSAGE_NOT_ENOUGH_MONEY = "구입 금액은 적어도 한 개의 로또를 구매할 수 있어야 합니다."
        private const val ERROR_MESSAGE_NOT_ENOUGH_MANUAL_LOTTOS = "수동으로 구매할 로또의 개수는 0보다 작을 수 없습니다."
        private const val ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS = "수동으로 구매할 로또의 개수는 로또의 총 개수를 초과할 수 없습니다."
    }
}
