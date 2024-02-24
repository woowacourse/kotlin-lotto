package view

import model.Lottos
import model.WinningStatistics

object OutputView {
    private const val PURCHASE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
    private const val WINNING_STATISTICS = "\n당첨 통계\n---------"
    private const val LOSING_MONEY = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"

    fun outputNumberOfLotto(
        numberOfManualLotto: Int,
        numberOfAutoLotto: Int,
    ) {
        println(PURCHASE.format(numberOfManualLotto, numberOfAutoLotto))
    }

    fun outputLottos(lottos: Lottos) {
        println(lottos)
    }

    fun outputWinningStatistics(winningStatistics: WinningStatistics) {
        println(WINNING_STATISTICS)
        println(winningStatistics)
    }

    fun outputRateOfReturn(rateOfReturn: Double) {
        print("총 수익률은 ${(rateOfReturn * 100).toInt() / 100.0}입니다.")
        if (rateOfReturn < 1) {
            println(LOSING_MONEY)
        }
    }
}
