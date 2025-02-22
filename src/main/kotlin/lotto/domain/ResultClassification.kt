package lotto.domain

enum class ResultClassification {
    PROFIT,
    LOSS,
    BREAKEVEN,
    ;

    companion object {
        fun from(profitRate: Double): ResultClassification =
            when {
                profitRate > 1.0 -> PROFIT
                profitRate < 1.0 -> LOSS
                else -> BREAKEVEN
            }
    }
}
