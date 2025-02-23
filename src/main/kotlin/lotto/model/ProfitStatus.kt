package lotto.model

enum class ProfitStatus(
    val krDescription: String,
) {
    PROFIT("이득"),
    LOSS("손해"),
    BREAK_EVEN("본전"),
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
