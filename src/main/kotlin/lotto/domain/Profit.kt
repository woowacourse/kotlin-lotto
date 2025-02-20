package lotto.domain

enum class Profit(val message: String) {
    LOSS("기준이 1이기 때문에 결과적으로 손해라는 의미임"),
    EVEN("손해도 없고, 이익도 없음"),
    GAIN("이익이 발생함"),
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
