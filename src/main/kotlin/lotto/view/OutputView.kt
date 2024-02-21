package lotto.view

import lotto.model.Lotto

object OutputView {
    fun printNumberOfTicket(numberOfTicket: Int) {
        println("${numberOfTicket}개를 구매했습니다.")
    }

    fun printLottoTickets(lottoTickets: List<Lotto>) {
        lottoTickets.forEach { println(it) }
        println()
    }

    fun printWinningStatus(winningStatus: String) {
        println("\n당첨 통계")
        println("---------")
        println(winningStatus)
    }

    fun printEarningRate(earningRate: Double) {
        println("총 수익률은 %.2f입니다.".format(earningRate))
    }
}
