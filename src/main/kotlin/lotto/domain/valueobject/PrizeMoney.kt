package lotto.domain.valueobject

@JvmInline
value class PrizeMoney(
    val money: Long,
) {
    init {
        require(money >= 0) { ERROR_NEGATIVE_NUMBER }
    }

    companion object {
        private const val ERROR_NEGATIVE_NUMBER = "당첨 금액은 음수가 될 수 없습니다."
    }
}
