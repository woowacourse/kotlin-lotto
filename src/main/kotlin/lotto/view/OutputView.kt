package lotto.view

import lotto.model.Lottos
import lotto.model.WinningStatistics

object OutputView {
    private const val PURCHASE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
    private const val WINNING_STATISTICS = "\n당첨 통계\n---------"
    private const val LOSING_MONEY = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
    private const val RATE_OF_RETURN_MESSAGE = "총 수익률은 %.2f입니다."
    private const val RATE_OF_RETURN_THRESHOLD = 1
    private const val DEFAULT_SEPARATOR = ", "
    private const val START = "["
    private const val END = "]"

    fun outputShowLottos(
        manualLottos: Lottos,
        autoLottos: Lottos,
    ) {
        println(PURCHASE.format(manualLottos.publishedLottos.size, autoLottos.publishedLottos.size))

        manualLottos.publishedLottos.forEach { lotto ->
            val formattedString =
                lotto.numbers
                    .sortedBy { it.number }
                    .joinToString(DEFAULT_SEPARATOR, START, END) { it.number.toString() }
            println(formattedString)
        }

        autoLottos.publishedLottos.forEach { lotto ->
            val formattedString =
                lotto.numbers
                    .sortedBy { it.number }
                    .joinToString(DEFAULT_SEPARATOR, START, END) { it.number.toString() }
            println(formattedString)
        }
    }

    fun outputWinningStatistics(winningStatistics: WinningStatistics) {
        println(WINNING_STATISTICS)
        println(winningStatistics)
    }

    fun outputRateOfReturn(rateOfReturn: Double) {
        print(RATE_OF_RETURN_MESSAGE.format(rateOfReturn * 100 / 100.0))
        if (rateOfReturn < RATE_OF_RETURN_THRESHOLD) {
            println(LOSING_MONEY)
        }
    }

    fun outputErrorMessage(exception: Throwable) {
        println(exception.message)
    }
}
