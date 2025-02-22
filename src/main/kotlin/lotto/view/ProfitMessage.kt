package lotto.view

import lotto.domain.Profit

object ProfitMessage {
    private val messages =
        mapOf(
            Profit.LOSS to "기준이 1이기 때문에 결과적으로 손해라는 의미임",
            Profit.EVEN to "손해도 없고, 이익도 없음",
            Profit.GAIN to "이익이 발생함",
        )

    fun getMessage(profit: Profit): String {
        return messages[profit] ?: ""
    }
}
