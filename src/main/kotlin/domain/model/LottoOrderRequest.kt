package domain.model

import domain.model.manual.ManualLottoAmount
import domain.model.price.PurchasePrice

data class LottoOrderRequest(
    val money: PurchasePrice,
    val amount: ManualLottoAmount,
    val manualLotto: List<List<Int>>,
) {
    val autoLottoAmount
        get() = money.getPurchasableLottoCount() - amount.value
}
