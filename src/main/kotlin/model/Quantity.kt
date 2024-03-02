package model

data class Quantity(val count: Int) {
    init {
        require(count >= DEFAULT_COUNT) { "수량은 0 이상이어야 합니다." }
    }

    operator fun plus(other: Quantity): Quantity = Quantity(count + other.count)

    operator fun minus(other: Quantity): Quantity = Quantity(count - other.count)

    companion object {
        private const val DEFAULT_COUNT = 0
    }
}
