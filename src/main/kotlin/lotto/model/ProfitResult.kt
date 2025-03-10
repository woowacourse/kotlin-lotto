package lotto.model

class ProfitResult(
    val profitRate: Float,
) {
    val profitStatus = ProfitStatus.from(profitRate)
}
