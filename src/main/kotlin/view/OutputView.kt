package view

import model.LottoResult
import model.LottoTicket
import model.Rank

class OutputView {
    fun printLottoCount(lottoCount: Int) {
        println("${lottoCount}개를 구매했습니다")
    }

    fun printLottoTickets(lottoTickets: List<LottoTicket>) {
        lottoTickets.forEach {
            println(it.lottoTicket.sorted())
        }
    }

    fun printWinningChart(lottoResult: LottoResult) {
        println("3개 일치 (${Rank.FIFTH.winningMoney}원)- ${lottoResult.getNum(Rank.FIFTH)}개")
        println("4개 일치 (${Rank.FOURTH.winningMoney}원)- ${lottoResult.getNum(Rank.FOURTH)}개")
        println("5개 일치 (${Rank.THIRD.winningMoney}원)- ${lottoResult.getNum(Rank.THIRD)}개")
        println("5개 일치, 보너스 볼 일치(${Rank.SECOND.winningMoney}원) - ${lottoResult.getNum(Rank.SECOND)}개")
        println("6개 일치 (${Rank.FIRST.winningMoney}원)- ${lottoResult.getNum(Rank.FIRST)}개")
    }

    fun printWinningRate(winningRate: Float) {
        println("총 수익률은 %.2f입니다.".format(winningRate))
    }
}