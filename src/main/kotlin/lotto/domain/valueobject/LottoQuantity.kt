package lotto.domain.valueobject

data class LottoQuantity(
    val quantity: Int,
) : Comparable<LottoQuantity> {
    init {
        require(quantity >= 0) { ERROR_NEGATIVE_NUMBER }
    }

    companion object {
        private const val ERROR_NEGATIVE_NUMBER = "로또 수량은 음수가 될 수 없습니다."
    }

    override fun compareTo(other: LottoQuantity): Int = this.quantity.compareTo(other.quantity)
}
