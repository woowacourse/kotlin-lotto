package lotto.model

enum class ProfitStatus {
    PROFIT,
    LOSS,
    BREAK_EVEN,
    ;

    companion object {
        fun from(profitRate: Float): ProfitStatus =
            when {
                profitRate > 1f -> PROFIT
                profitRate < 1f -> LOSS
                else -> BREAK_EVEN
            }
    }
}
