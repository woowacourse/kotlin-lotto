package view

import model.Lotto
import model.LottoGameResult
import model.Rank

class ConsoleLottoGameOutputView : LottoGameOutputView {
    override fun showPurchasedLottie(lottie: List<Lotto>) {
        println(MESSAGE_PURCHASED_LOTTIE_COUNT.format(lottie.size))
        lottie.forEach { println(it.numbers.map { it.number }) }
        println()
    }

    override fun showGameResult(
        results: List<LottoGameResult.RankResult>,
        rate: Double,
    ) {
        println(MESSAGE_TITLE)
        showRankState(results)
        showEarningRate(rate)
    }

    private fun showRankState(results: List<LottoGameResult.RankResult>) {
        val rankState =
            buildString {
                results.forEach { append(formatRankResult(it)) }
            }
        println(rankState)
    }

    private fun showEarningRate(rate: Double) {
        if (rate > 1) {
            println(MESSAGE_EARNING_RATE.format(rate) + MESSAGE_LOSS)
        } else {
            println(MESSAGE_EARNING_RATE.format(rate))
        }
    }

    private fun formatRankResult(rankResult: LottoGameResult.RankResult): String {
        val (rank, count) = rankResult
        return "${rank.countOfMatch}개 일치, ${formatBonusMessage(rankResult)}(${rank.winningMoney}원)- ${count}개\n"
    }

    private fun formatBonusMessage(rankResult: LottoGameResult.RankResult): String {
        return if (rankResult.rank == Rank.SECOND) "보너스 볼 일치" else ""
    }

    companion object {
        private const val MESSAGE_TITLE = "\n당첨 통계\n---------"
        private const val MESSAGE_PURCHASED_LOTTIE_COUNT = "%d개를 구매했습니다."
        private const val MESSAGE_EARNING_RATE = "총 수익률은 %.2f입니다."
        private const val MESSAGE_LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"
    }
}

fun main() {
    println(
        buildString {
            append(1)
            append("\n\n")
            append(2)
        },
    )
}
