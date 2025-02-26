package domain.model.price

import domain.model.price.PurchasePriceException.InvalidUnitException
import domain.model.price.PurchasePriceException.OverStandardPriceException
import domain.service.LottoMachine.Companion.STANDARD_AMOUNT_UNIT

class PurchasePrice(
    val value: Int,
    standardPrice: Int = STANDARD_AMOUNT_UNIT,
) {
    init {
        if (value <= 0 && value < standardPrice) {
            throw OverStandardPriceException(standardPrice)
        }
        if (value % standardPrice != 0) {
            throw InvalidUnitException(standardPrice)
        }
    }

    fun getPurchasableLottoCount(standardPrice: Int = STANDARD_AMOUNT_UNIT): Int {
        return value / standardPrice
    }
}

