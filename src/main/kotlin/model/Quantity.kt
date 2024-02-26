package model

data class Quantity(val count: Int = DEFAULT_PRIZE_COUNT) {
    init {
        require(count >= 0) { "수량은 0 이상이어야 합니다." }
    }

    operator fun plus(other: Quantity): Quantity = Quantity(count + other.count)

    companion object {
        private const val DEFAULT_PRIZE_COUNT = 0
    }
}
