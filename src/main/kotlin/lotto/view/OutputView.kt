package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.LottoDrawingResult
import lotto.domain.model.Rank

object OutputView {

    fun printInputManualNumberMessage() {
        println("\n수동으로 구매할 번호를 입력해 주세요.")
    }

    fun printInputWinningNumberMessage() {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
    }

    fun printLottoQuantity(manualLottoQuantity: Int, autoLottoQuantity: Int) {
        println("\n수동으로 ${manualLottoQuantity}장, 자동으로 ${autoLottoQuantity}개를 구매했습니다.")
    }

    fun printLottoNumbers(lottoTickets: List<Lotto>) {
        lottoTickets.forEach {
            println(it.numbers.map { it.value }.joinToString(prefix = "[", separator = ", ", postfix = "]"))
        }
    }

    fun printLottoResult(lottoDrawingResult: LottoDrawingResult) {
        println("\n당첨 통계\n---------")
        val resultWithoutMiss = lottoDrawingResult.resultStatistics.filter { it.key != Rank.MISS }
        resultWithoutMiss.forEach { (rank, count) ->
            if (rank.isBonusMatch) {
                println("${rank.countOfMatch}개 일치, 보너스 볼 일치(${rank.winningMoney}원)- ${count}개")
            } else {
                println("${rank.countOfMatch}개 일치 (${rank.winningMoney}원)- ${count}개")
            }
        }
    }

    fun printMargin(marginRate: Double) {
        print("총 수익률은 ${formatDouble(marginRate)}입니다.")
        if (marginRate < 1) print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)")
    }

    private fun formatDouble(value: Double): String {
        return if (value % 1.0 == 0.0) {
            String.format("%.0f", value)
        } else {
            String.format("%.2f", value)
        }
    }
}
