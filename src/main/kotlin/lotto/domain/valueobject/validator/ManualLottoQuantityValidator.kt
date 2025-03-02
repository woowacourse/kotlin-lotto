package lotto.domain.valueobject.validator

import lotto.domain.valueobject.LottoPaymentMoney
import lotto.domain.valueobject.ObjectQuantity

class ManualLottoQuantityValidator {
    fun validate(
        lottoPaymentMoney: LottoPaymentMoney,
        manualLottoQuantity: ObjectQuantity,
    ) {
        val possibleQuantity = lottoPaymentMoney.calculatePossibleBuyLottoQuantity()
        require(possibleQuantity >= manualLottoQuantity) {
            ERROR_NOT_ALLOW_MANUAL_QUANTITY.format(manualLottoQuantity.quantity, possibleQuantity.quantity)
        }
    }

    companion object {
        private const val ERROR_NOT_ALLOW_MANUAL_QUANTITY = "수동 로또 수량(%d)은 구입 가능한 수량(%d)을 초과할 수 없습니다."
    }
}
