package lotto.domain.model.winning

enum class GainLoss {
    GAIN,
    PRINCIPAL,
    LOSS,
    ;

    companion object {
        fun valueOf(rate: Double): GainLoss {
            if (rate < 1.0) {
                return LOSS
            }
            if (rate > 1.0) {
                return GAIN
            }
            return PRINCIPAL
        }
    }
}
