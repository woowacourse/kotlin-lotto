package domain.model

import domain.model.lotto.Lotto
import domain.model.manual.ManualLottoAmount
import domain.model.price.PurchasePrice

data class LottoOrderRequest(
    val money: PurchasePrice,
    val amount: ManualLottoAmount,
    val manualLotto: List<Lotto>,
) {
    val autoLottoAmount
        get() = money.getPurchasableLottoCount() - amount.value

    fun combine(autoLottoNumbers: List<Lotto>): List<Lotto> {
        return manualLotto + autoLottoNumbers
    }
}
