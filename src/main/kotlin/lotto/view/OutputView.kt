package lotto.view

import lotto.model.Lotto
import lotto.model.WinningStatusChecker
import lotto.util.WinningRank

object OutputView {
    fun printNumberOfTicket(numberOfTicket: Int) {
        println("${numberOfTicket}개를 구매했습니다.")
    }

    fun printLottoTickets(lottoTickets: List<Lotto>) {
        lottoTickets.forEach { println(it) }
        println()
    }

    fun printWinningStatus(winningStatus: WinningStatusChecker) {
        println("\n당첨 통계")
        println("---------")
        WinningRank.entries.filter { it != WinningRank.MISS }.forEach {
            print("${it.countOfMatch}개 일치")
            if (it == WinningRank.SECOND) print(", 보너스 볼 일치")
            print(" (${it.winningMoney}원)")
            println(" - ${winningStatus.getWinningCountsByRank(it)}개")
        }
    }

    fun printEarningRate(earningRate: Double) {
        println("총 수익률은 %.2f입니다.".format(earningRate))
    }
}
