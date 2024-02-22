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
}