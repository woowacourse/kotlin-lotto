package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoResult
import lotto.domain.Profit
import lotto.domain.Rank

object OutputView {
    fun printLottoAmount(amount: Int) {
        println("${amount}개를 구매했습니다.")
    }

    fun printLottos(lottos: List<Lotto>) {
        lottos.forEach { lotto ->
            println(lotto.lottoNums.map { it.number }.joinToString(", ", "[", "]"))
        }
    }

    fun printResult(lottoResult: LottoResult) {
        printHeader()
        printStatistics(lottoResult)
    }

    fun printProfit(profitRate: Double) {
        val profitStatus = Profit.profitOf(profitRate)
        println(
            "총 수익률은 ${"%.2f".format(profitRate)}입니다. (${profitStatus.message})",
        )
    }

    fun printErrorMessage(message: String?) {
        println(message)
    }

    private fun printHeader() {
        println("\n당첨 통계")
        println("---------")
    }

    private fun printStatistics(lottoResult: LottoResult) {
        Rank.entries.reversed()
            .filter { it != Rank.MISS }
            .forEach { rank ->
                println(formatWinningMessage(rank, lottoResult))
            }
    }

    private fun formatWinningMessage(
        rank: Rank,
        lottoResult: LottoResult,
    ): String {
        val bonusText = if (rank == Rank.SECOND) ", 보너스 볼 일치" else ""
        val count = lottoResult.getWinningStatistics().getOrDefault(rank, 0)

        return "${rank.countOfMatch}개 일치$bonusText (${rank.winningMoney}원) - ${count}개"
    }
}
