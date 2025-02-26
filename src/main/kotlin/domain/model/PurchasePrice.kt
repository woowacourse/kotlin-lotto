package domain.model

import domain.service.LottoMachine.Companion.STANDARD_AMOUNT_UNIT
import util.ErrorConstants.ERROR

class PurchasePrice(
    val value: Int,
    private val standardPrice: Int = STANDARD_AMOUNT_UNIT,
) {
    init {
        require(value > 0 && value >= standardPrice) { "$ERROR ${standardPrice}원 이상 입력해주세요." }
        require(value % standardPrice == 0) { "$ERROR ${standardPrice}원 단위로 입력해주세요." }
    }

    fun getPurchasableLottoCount(standardPrice: Int = STANDARD_AMOUNT_UNIT): Int {
        return value / standardPrice
    }
}
