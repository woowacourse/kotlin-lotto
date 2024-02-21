package view

import model.Lotto
import model.LottoDrawingResult
import model.Rank

object OutputView {

    fun printLottoQuantity(quantity: Int) {
        println("${quantity}개를 구매했습니다.")
    }

    fun printLottoNumbers(lottoTickets: List<Lotto>) {
        lottoTickets.forEach {
            println(it.numbers.map { it.value }.joinToString(prefix = "[", separator = ", ", postfix = "]"))
        }
    }

    fun printLottoResult(lottoDrawingResult: LottoDrawingResult) {
        println("당첨 통계\n---------")
        lottoDrawingResult.statistics.forEach { (rank, count) ->
            if (rank == Rank.SECOND) {
                println("${rank.countOfMatch}개 일치, 보너스 볼 일치(${rank.winningMoney}원)- ${count}개")
            } else {
                println("${rank.countOfMatch}개 일치 (${rank.winningMoney}원)- ${count}개")
            }
        }
    }
}
