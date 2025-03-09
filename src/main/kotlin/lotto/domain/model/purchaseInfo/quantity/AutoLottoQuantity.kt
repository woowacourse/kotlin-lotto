package lotto.domain.model.purchaseInfo.quantity

@JvmInline
value class AutoLottoQuantity private constructor(
    val quantity: Int,
) : Comparable<AutoLottoQuantity> {
    init {
        require(quantity >= 0) { ERROR_AUTO_LOTTO_NUMBER_UNDER_ZERO }
    }

    constructor(boughtLottoQuantity: LottoQuantity, manualLottoQuantity: ManualLottoQuantity) : this(
        quantity = boughtLottoQuantity.quantity - manualLottoQuantity.quantity,
    )

    override fun compareTo(other: AutoLottoQuantity): Int = this.quantity.compareTo(other.quantity)

    companion object {
        private const val ERROR_AUTO_LOTTO_NUMBER_UNDER_ZERO = "자동 로또 수량은 최소 0 이상의 값이어야 합니다"
    }
}
