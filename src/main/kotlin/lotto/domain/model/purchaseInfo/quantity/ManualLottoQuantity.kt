package lotto.domain.model.purchaseInfo.quantity

@JvmInline
value class ManualLottoQuantity private constructor(
    val quantity: Int,
) : Comparable<ManualLottoQuantity> {
    init {
        require(quantity >= 0) { ERROR_MANUAL_LOTTO_NUMBER_UNDER_ZERO }
    }

    constructor(boughtLottoQuantity: LottoQuantity, rawManualLottoQuantity: Int) : this(
        quantity = rawManualLottoQuantity,
    ) {
        require(boughtLottoQuantity.quantity >= rawManualLottoQuantity) {
            ERROR_NOT_ALLOW_MANUAL_QUANTITY.format(rawManualLottoQuantity, boughtLottoQuantity.quantity)
        }
    }

    override fun compareTo(other: ManualLottoQuantity): Int = this.quantity.compareTo(other.quantity)

    companion object {
        private const val ERROR_MANUAL_LOTTO_NUMBER_UNDER_ZERO = "수동 로또 수량은 최소 0 이상의 값이어야 합니다"
        private const val ERROR_NOT_ALLOW_MANUAL_QUANTITY = "수동 로또 수량(%d)은 구입 가능한 수량(%d)을 초과할 수 없습니다."
    }
}
