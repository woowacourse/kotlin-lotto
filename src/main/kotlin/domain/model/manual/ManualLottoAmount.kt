package domain.model.manual

import domain.model.price.PurchasePrice

@JvmInline
value class ManualLottoAmount private constructor(val value: Int) {
    constructor(
        lottoAmount: Int,
        money: PurchasePrice,
    ) : this(lottoAmount) {
        val purchasableAmount = money.getPurchasableLottoCount()
        if (lottoAmount > purchasableAmount) {
            throw ManualLottoAmountException.InvalidPurchasableManualLottoSize()
        }
    }
}
