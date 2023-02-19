package view

import domain.Lotto
import domain.Lottos
import domain.Money
import domain.Rank
import domain.WinningResult

class OutputView {
    fun outputLottoSizeMessage(money: Money) {
        println((money.lottoCount(money.price)).toString() + OUTPUT_LOTTO_SIZE_MESSAGE)
    }

    fun outputLottos(lottos: Lottos) {
        val lottosResult = StringBuilder()
        lottos.lottos.forEach { lotto -> lottosResult.append(outputLotto(lotto) + "\n") }
        println(lottosResult)
        println()
    }

    fun outputLotto(lotto: Lotto): String {
        val lottoNumbers = lotto.numbers.map { it.getNumber() }
        return lottoNumbers.joinToString(prefix = PREFIX_MARK, separator = SEPARATOR_MARK, postfix = POSTFIX_MARK)
    }

    fun outputWinningResult(winningResult: WinningResult) {
        println(OUTPUT_WINNING_RESULT_MESSAGE)
        println(SEPARATE_MESSAGE)
        winningCatalog(winningResult)
    }

    private fun winningCatalog(winningResult: WinningResult) {
        val catalog = StringBuilder()
        val rankResult = listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
        rankResult.forEach {
            catalog.append(catalogText(it, winningResult) + "\n")
        }
        println(catalog)
    }
    private fun catalogText(rankResult: Rank, winningResult: WinningResult): String {
        return when (rankResult) {
            Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.FIRST -> RANK_MESSAGE.format(rankResult.countOfMatch, rankResult.winningMoney) + winningResult.result[rankResult] + "개"
            Rank.SECOND -> RANK_BONUS_MESSAGE.format(rankResult.countOfMatch, rankResult.winningMoney) + winningResult.result[rankResult] + "개"
            Rank.MISS -> ""
        }
    }

    fun outputYield(yield: Double) {
        println(YIELD_MESSAGE.format(yield))
    }

    companion object {
        const val OUTPUT_LOTTO_SIZE_MESSAGE = "개를 구매했습니다."
        const val PREFIX_MARK = "["
        const val SEPARATOR_MARK = ", "
        const val POSTFIX_MARK = "]"
        const val OUTPUT_WINNING_RESULT_MESSAGE = "당첨 통계"
        const val SEPARATE_MESSAGE = "---------"
        const val RANK_MESSAGE = "%d개 일치 (%d원)- "
        const val RANK_BONUS_MESSAGE = "%d개 일치, 보너스 볼 일치(%d원)- "
        const val YIELD_MESSAGE = "총 수익률은 %.2f입니다."
    }
}
