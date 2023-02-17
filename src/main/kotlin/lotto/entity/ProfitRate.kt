package lotto.entity

class ProfitRate(val value: Float) {
    fun isProfit(): Boolean = value >= PROFIT_STANDARD

    companion object {
        private const val PROFIT_STANDARD = 1.0f
    }
}
