package domain.model.price

import domain.service.LottoMachine.Companion.STANDARD_AMOUNT_UNIT
import util.ErrorConstants.ERROR

sealed class PurchasePriceException(override val message: String) : Throwable(message) {
    data class OverStandardPriceException(
        private val standardPrice: Int = STANDARD_AMOUNT_UNIT,
    ) : PurchasePriceException("$ERROR ${standardPrice}원 이상 입력해주세요.")

    data class InvalidUnitException(
        private val standardPrice: Int = STANDARD_AMOUNT_UNIT,
    ) : PurchasePriceException("$ERROR ${standardPrice}원 단위로 입력해주세요.")
}
