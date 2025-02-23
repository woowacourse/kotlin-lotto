package domain.model

data class LottoOrderRequest(
    val money: PurchasePrice,
    val amount: PassivityLottoAmount,
    val passiveLottoNumber: List<Lotto>,
)
