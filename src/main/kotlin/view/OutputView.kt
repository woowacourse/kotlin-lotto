package view

import lotto.domain.LottoTicket
import lotto.domain.Rank

object OutputView {
    fun printLottoTickets(lottoTickets: List<LottoTicket>) {
        lottoTickets
            .map { it.lottoNumbers }
            .map { it.map { lottoNumber -> lottoNumber.number } }
            .forEach { println(it) }
    }

    fun printLottoResult(lottoResult: Map<Rank, Int>, profit: Double) {
        println("당첨 통계")
        println("---------")
        lottoResult.entries
            .filter { it.key != Rank.NONE }
            .forEach { println(String.format("%d개 일치 (%d원) - %d개", it.key.countOfSameNumber, it.key.prize, it.value)) }
        println(String.format("총 수익률은 %.2f입니다.", profit))
    }
}
