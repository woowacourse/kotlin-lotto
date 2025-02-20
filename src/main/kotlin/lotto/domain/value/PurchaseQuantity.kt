package lotto.domain.value

import lotto.constants.LottoConstants
import kotlin.runCatching

class PurchaseQuantity(
    purchaseAmount: PurchaseAmount,
) {
    val quantity =
        runCatching { purchaseAmount.amount / LottoConstants.LOTTO_PRICE }.getOrElse {
            throw IllegalArgumentException(
                INDIVISIBLE_VALUE_ERROR.format(
                    purchaseAmount.amount,
                ),
            )
        }

    companion object {
        private const val INDIVISIBLE_VALUE_ERROR = "%d는 ${LottoConstants.LOTTO_PRICE}로 나눌 수 없습니다."
    }
}
