package domain.model.price

import domain.model.machine.LottoMachine.Companion.STANDARD_AMOUNT_UNIT
import domain.model.price.PurchasePriceException.InvalidUnitException
import domain.model.price.PurchasePriceException.OverStandardPriceException

class PurchasePrice(
    val value: Int,
    private val standardPrice: Int = STANDARD_AMOUNT_UNIT,
) {
    init {
        check(value >= standardPrice) {
            throw OverStandardPriceException(standardPrice)
        }
        check(value % standardPrice == 0) {
            throw InvalidUnitException(standardPrice)
        }
    }

    fun getPurchasableLottoCount(): Int {
        return value / standardPrice
    }
}
