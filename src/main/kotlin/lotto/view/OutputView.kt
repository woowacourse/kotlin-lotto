package lotto.view

import lotto.domain.model.LottoPurchaseInfo
import lotto.domain.model.Rank

class OutputView {
    fun printAmountMessage() {
        println("구입금액을 입력해 주세요.")
    }

    fun printManualLottoCountMessage() {
        println()
        println("수동으로 구매할 로또 수를 입력해 주세요.")
    }

    fun printManualLottoNumberMessage() {
        println()
        println("수동으로 구매할 번호를 입력해 주세요.")
    }

    fun printPublishedLotto(
        lottoPurchaseInfo: LottoPurchaseInfo,
        publishedLotto: List<String>,
    ) {
        println()
        println("수동으로 ${lottoPurchaseInfo.manualLottoCount}장, 자동으로 ${lottoPurchaseInfo.getAutoLottoQuantity()}개를 구매했습니다.")
        publishedLotto.forEach { println(it) }
    }

    fun printWinningNumberMessage() {
        println()
        println("지난 주 당첨 번호를 입력해 주세요.")
    }

    fun printBonusMessage() {
        println("보너스 볼을 입력해 주세요.")
    }

    fun printPrize(
        result: Map<Rank, Int>,
        earningRate: Double,
    ) {
        println()
        println("당첨 통계")
        println("---------")
        result.forEach { (rank, matchCount) ->
            if (rank == Rank.MISS) return@forEach
            printRankResult(rank.countOfMatch, rank.winningMoney, matchCount, rank == Rank.SECOND)
        }
        println("총 수익률은 ${"%.2f".format(earningRate)}입니다.")
    }

    private fun printRankResult(
        countOfMatch: Int,
        winningMoney: Int,
        userCount: Int,
        bonus: Boolean = false,
    ) {
        val bonusText = if (bonus) ", 보너스 볼 일치" else ""
        println("${countOfMatch}개 일치$bonusText (${winningMoney}원)- ${userCount}개")
    }
}
