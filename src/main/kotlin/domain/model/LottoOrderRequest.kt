package domain.model

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
