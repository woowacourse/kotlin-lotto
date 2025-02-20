package lotto.view

import lotto.domain.LottoResult
import lotto.domain.Rank

object OutputView {
    fun printLottoAmount(amount: Int) {
        println("${amount}개를 구매했습니다.")
    }

    fun printLottos(lottos: List<List<Int>>) {
        lottos.forEach { lotto ->
            println(lotto.joinToString(", ", "[", "]"))
        }
    }

    fun printResult(lottoResult: LottoResult) {
        println("\n당첨 통계")
        println("---------")

        Rank.entries.reversed()
            .filter { it != Rank.MISS }
            .forEach { rank ->
                println(
                    "${rank.countOfMatch}개 일치${if (rank == Rank.SECOND) ", 보너스 볼 일치" else ""} (${rank.winningMoney}원) - ${
                        lottoResult.getWinningStatistics().getOrDefault(
                            rank,
                            0,
                        )
                    }개",
                )
            }
    }

    fun printProfit(profitRate: Double) {
        println(
            "총 수익률은 ${
                String.format(
                    "%.2f",
                    profitRate,
                )
            }입니다. (${if (profitRate < 1) "기준이 1이기 때문에 결과적으로 손해라는 의미임" else "이익이 발생함"})",
        )
    }
}
