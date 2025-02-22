package lotto.domain

enum class Profit() {
    LOSS,
    EVEN,
    GAIN,
    ;

    companion object {
        fun profitOf(profit: Double): Profit {
            return when {
                profit > 1 -> GAIN
                profit < 1 -> LOSS
                else -> EVEN
            }
        }
    }
}
