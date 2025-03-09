package lotto.domain.model.winning

enum class GainLoss {
    GAIN,
    PRINCIPAL,
    LOSS,
    ;

    companion object {
        fun valueOf(rate: Double): GainLoss =
            when {
                rate < 1.0 -> LOSS
                rate > 1.0 -> GAIN
                else -> PRINCIPAL
            }
    }
}
