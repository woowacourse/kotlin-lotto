package lotto.domain

enum class Profit(val profitMessage: ProfitMessage) {
    LOSS(ProfitMessage.LossMessage),
    EVEN(ProfitMessage.EvenMessage),
    GAIN(ProfitMessage.GainMessage),
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

sealed class ProfitMessage {
    object LossMessage : ProfitMessage() {
        override fun toString(): String = "기준이 1이기 때문에 결과적으로 손해라는 의미임"
    }

    object EvenMessage : ProfitMessage() {
        override fun toString(): String = "손해도 없고, 이익도 없음"
    }

    object GainMessage : ProfitMessage() {
        override fun toString(): String = "이익이 발생함"
    }
}
