package lotto.view

import lotto.model.Lotto

object OutputView {
    fun printNumberOfTicket(numberOfTicket: Int) {
        println("${numberOfTicket}개를 구매했습니다.")
    }

    fun printLottoTickets(lottoTickets: List<Lotto>) {
        lottoTickets.forEach { println(it) }
    }
}
