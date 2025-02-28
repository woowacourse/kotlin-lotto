package domain.model.price

import domain.model.machine.LottoMachine.Companion.STANDARD_AMOUNT_UNIT
import domain.model.price.PurchasePriceException.InvalidUnitException
import domain.model.price.PurchasePriceException.OverStandardPriceException

class PurchasePrice(
    val value: Int,
    val standardPrice: Int = STANDARD_AMOUNT_UNIT,
) {
    init {
        if (value <= 0 && value < standardPrice) {
            throw OverStandardPriceException(standardPrice)
        }
        if (value % standardPrice != 0) {
            throw InvalidUnitException(standardPrice)
        }
    }

    fun getPurchasableLottoCount(): Int {
        return value / standardPrice
    }
}
