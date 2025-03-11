package lotto.model

enum class ProfitStatus {
    PROFIT,
    LOSS,
    BREAK_EVEN,
    ;

    companion object {
        private const val DEFAULT_PROFIT_CRITERIA = 1f

        fun from(profitRate: Float): ProfitStatus =
            when {
                profitRate > DEFAULT_PROFIT_CRITERIA -> PROFIT
                profitRate < DEFAULT_PROFIT_CRITERIA -> LOSS
                else -> BREAK_EVEN
            }
    }
}
