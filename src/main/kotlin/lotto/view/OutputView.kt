package lotto.view

import lotto.model.Lotto
import lotto.model.Rank
import lotto.model.LottoDrawingResult
import lotto.model.Margin

class OutputView {

    fun printNumberOfTicket(number: Int) {
        println("${number}개를 구매했습니다.")
    }

    fun printLottoNumbers(lottoTickets: List<Lotto>) {
        lottoTickets.forEach { lotto ->
            println(lotto.numbers.map { it.value }.joinToString(prefix = "[", separator = ", ", postfix = "]"))
        }
    }

    fun printLottoResult(lottoDrawingResult: LottoDrawingResult) {
        println("\n당첨 통계\n---------")
        val resultWithoutMiss = lottoDrawingResult.statistics.filter { it.key != Rank.MISS }
        resultWithoutMiss.forEach { (rank, count) ->
            if (rank == Rank.SECOND) {
                println("${rank.countOfMatch}개 일치, 보너스 볼 일치(${rank.winningMoney}원)- ${count}개")
            } else {
                println("${rank.countOfMatch}개 일치 (${rank.winningMoney}원)- ${count}개")
            }
        }
    }

    fun printMargin(marginRate: Margin) {
        print("총 수익률은 ${formatMargin(marginRate.rate)}입니다.")
        if (marginRate.rate < 1) print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)")
    }

    private fun formatMargin(value: Double): String {
        return if (value % 1.0 == 0.0) {
            String.format("%.0f", value)
        } else {
            String.format("%.2f", value)
        }
    }
}
