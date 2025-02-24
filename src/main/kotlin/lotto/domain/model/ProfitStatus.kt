package lotto.domain.model

enum class ProfitStatus {
    GAIN,
    LOSS,
    BREAKEVEN,
    ;

    companion object {
        fun getMessage(profit: Double): ProfitStatus =
            when {
                profit > 1.0 -> GAIN
                profit < 1.0 -> LOSS
                else -> BREAKEVEN
            }
    }
}
