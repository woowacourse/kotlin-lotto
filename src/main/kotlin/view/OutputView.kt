package view

import model.LottoTicket
import model.Rank

class OutputView {
    fun printLottoCount(lottoCount: Int){
        println("${lottoCount}개를 구매했습니다")
    }

    fun printLottoTickets(lottoTickets: List<LottoTicket>){
        lottoTickets.forEach{
            println(it.lottoTicket.sorted())
        }
    }

    fun printWinningChart(winningChart: Map<Rank, Int>){
        println("3개 일치 (5,000원)- ${winningChart[Rank.FIFTH]?: 0}개")
        println("4개 일치 (50,000원)- ${winningChart[Rank.FOURTH]?: 0}개")
        println("5개 일치 (1,500,000원)- ${winningChart[Rank.THIRD]?: 0}개")
        println("5개 일치, 보너스 볼 일치(30,000,000원) - ${winningChart[Rank.SECOND]?: 0}개")
        println("6개 일치 (2,000,000,000원)- ${winningChart[Rank.FIRST]?: 0}개")
    }

    fun printWinningRate(winningRate: Float) {
        println("총 수익률은 ${winningRate}입니다.")
    }
}