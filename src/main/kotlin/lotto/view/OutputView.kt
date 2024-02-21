package lotto.view

import lotto.model.Lotto

object OutputView {
    fun printNumberOfTicket(numberOfTicket: Int) {
        println("${numberOfTicket}개를 구매했습니다.")
    }

    fun printLottoTickets(lottoTickets: List<Lotto>) {
        lottoTickets.forEach { println(it) }
    }

    fun printEarningRate(earningRate: Double) {
        println("총 수익률은 ${earningRate}입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)")
    }
}
