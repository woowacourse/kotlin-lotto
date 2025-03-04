package lotto.domain.valueobject

@JvmInline
value class WinningQuantity(
    val quantity: Int,
) : Comparable<WinningQuantity> {
    init {
        require(quantity >= 0) { ERROR_NEGATIVE_WINNING_NUMBER }
    }

    override fun compareTo(other: WinningQuantity): Int = this.quantity.compareTo(other.quantity)

    companion object {
        private const val ERROR_NEGATIVE_WINNING_NUMBER = "당첨 수량은 음수가 될 수 없습니다."
    }
}
