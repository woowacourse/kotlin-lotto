package lotto.domain.model.purchaseInfo

import lotto.domain.model.purchaseInfo.quantity.LottoQuantity
import kotlin.require

@JvmInline
value class LottoPaymentMoney(
    val money: Int,
) {
    init {
        require(money >= LOTTO_PRICE_UNIT) { ERROR_MINIMUM_NUMBER }
        require(money % LOTTO_PRICE_UNIT == 0) { ERROR_DIVIDE_BY_LOTTO_UNIT }
    }

    fun calculatePossibleBuyLottoQuantity(): LottoQuantity = LottoQuantity(money / LOTTO_PRICE_UNIT)

    companion object {
        private const val LOTTO_PRICE_UNIT = 1000

        private const val ERROR_MINIMUM_NUMBER = "로또 구입 금액은 로또 한장보다 작을 수 없습니다."
        private const val ERROR_DIVIDE_BY_LOTTO_UNIT = "로또 구입 금액은 ${LOTTO_PRICE_UNIT}원 단위로 지불해야 합니다."
    }
}
