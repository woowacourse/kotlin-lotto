package lotto.domain.valueobject

data class ObjectQuantity(
    val quantity: Int,
) : Comparable<ObjectQuantity> {
    init {
        require(quantity >= 0) { ERROR_NEGATIVE_NUMBER }
    }

    override fun compareTo(other: ObjectQuantity): Int = this.quantity.compareTo(other.quantity)

    companion object {
        private const val ERROR_NEGATIVE_NUMBER = "수량은 음수가 될 수 없습니다."
    }
}
