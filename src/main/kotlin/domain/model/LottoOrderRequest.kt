package domain.model

data class LottoOrderRequest(
    val money: PurchasePrice,
    val amount: PassivityLottoAmount,
    val passiveLottoNumber: List<Lotto>,
) {
    val quickPickLottoAmount
        get() = money.getPurchasableLottoCount() - amount.value
}
