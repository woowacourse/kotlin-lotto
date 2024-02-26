package model

data class Quantity(val count: Int = DEFAULT_COUNT) {
    init {
        require(count >= DEFAULT_COUNT) { "수량은 0 이상이어야 합니다." }
    }

    operator fun plus(other: Quantity): Quantity = Quantity(count + other.count)

    operator fun minus(other: Quantity): Quantity = Quantity(count - other.count)

    companion object {
        private const val DEFAULT_COUNT = 0

        fun from(input: String): Quantity {
            val count = input.toIntOrNull()
            requireNotNull(count) { "잘못된 수량 형식입니다." }
            return Quantity(count)
        }
    }
}
