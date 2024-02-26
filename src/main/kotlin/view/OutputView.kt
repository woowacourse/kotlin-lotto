package view

import model.Lottos
import model.WinningStatistics

object OutputView {
    private const val NUMBER_OF_LOTTO = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
    private const val WINNING_STATISTICS = "당첨 통계"
    private const val DIVIDING_LINE = "---------"
    private const val RATE_OF_RETURN = "총 수익률은 %.2f입니다."
    private const val LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"

    fun outputNumberOfLotto(
        numberOfHandpickedLotto: Int,
        numberOfAutomaticLotto: Int,
    ) {
        println()
        println(NUMBER_OF_LOTTO.format(numberOfHandpickedLotto, numberOfAutomaticLotto))
    }

    fun outputLottos(lottos: Lottos) {
        println(lottos)
    }

    fun outputWinningStatistics(winningStatistics: WinningStatistics) {
        println()
        println(WINNING_STATISTICS)
        println(DIVIDING_LINE)
        println(winningStatistics)
    }

    fun outputRateOfReturn(rateOfReturn: Double) {
        println("${RATE_OF_RETURN.format((rateOfReturn * 100).toInt() / 100.0)}${LOSS.takeIf { rateOfReturn < 1 } ?: ""}")
    }
}
