package lotto.domain.model.purchaseInfo.quantity

@JvmInline
value class LottoQuantity(
    val quantity: Int,
) : Comparable<LottoQuantity> {
    init {
        require(quantity >= 1) { ERROR_NEGATIVE_LOTTO_NUMBER }
    }

    override fun compareTo(other: LottoQuantity): Int = this.quantity.compareTo(other.quantity)

    companion object {
        private const val ERROR_NEGATIVE_LOTTO_NUMBER = "로또 수량은 최소 1 이상의 값이어야 합니다"
    }
}
