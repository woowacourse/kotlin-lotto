package view

import model.Lottos
import model.WinningStatistics


object OutputView {
    fun outputNumberOfLotto(numberOfLotto: Int) {
        println("${numberOfLotto}개를 구매했습니다.")
    }

    fun outputLottos(lottos: Lottos) {
        println(lottos)
    }

    fun outputWinningStatistics(winningStatistics: WinningStatistics) {
        println()
        println("당첨 통계")
        println("---------")
        println(winningStatistics)
    }

    fun outputRateOfReturn(rateOfReturn: Double) {
        println("총 수익률은 ${(rateOfReturn * 100).toInt() / 100.0}입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)")
    }
}
