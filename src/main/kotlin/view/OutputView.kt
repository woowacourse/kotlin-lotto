package view

import model.LottoTicket

class OutputView {
    fun printLottoCount(lottoCount: Int){
        println("${lottoCount}개를 구매했습니다")
    }

    fun printLottoTickets(lottoTickets: List<LottoTicket>){
        lottoTickets.forEach{
            println(it.lottoTicket.sorted())
        }
    }
}