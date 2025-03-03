package lotto.domain.valueobject

import kotlin.require

@JvmInline
value class LottoPaymentMoney(
    val money: Int,
) {
    init {
        require(money >= 0) { ERROR_NEGATIVE_NUMBER }
        require(money % LOTTO_PRICE_UNIT == 0) { ERROR_DIVIDE_BY_LOTTO_UNIT }
    }

    fun calculatePossibleBuyLottoQuantity(): ObjectQuantity = ObjectQuantity(money / LOTTO_PRICE_UNIT)

    fun calculateLeftLotoQuantity(partialPurchaseQuantity: ObjectQuantity): ObjectQuantity {
        val rawLeftQuantity = calculatePossibleBuyLottoQuantity().quantity - partialPurchaseQuantity.quantity
        return ObjectQuantity(rawLeftQuantity)
    }

    companion object {
        private const val LOTTO_PRICE_UNIT = 1000

        private const val ERROR_NEGATIVE_NUMBER = "로또 구입 금액은 음수가 될 수 없습니다."
        private const val ERROR_DIVIDE_BY_LOTTO_UNIT = "로또 구입 금액은 ${LOTTO_PRICE_UNIT}원 단위로 지불해야 합니다."
    }
}
