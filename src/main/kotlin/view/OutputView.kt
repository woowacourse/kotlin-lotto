package view

import domain.Rank
import model.Lotto

class OutputView {

    fun printInputMoney() = println(ENTER_MONEY)
    fun printLottoCount(count: Int) = println("$count" + PURCHASE_COUNT)
    fun printInputWinningNumbers() = println(ENTER_WINNING_NUMBER)
    fun printInputBonusNumber() = println(ENTER_BONUS_NUMBER)

    fun printLotto(lotto: Lotto) {
        val lottoNumbers = mutableListOf<Int>()
        lotto.lottoNumbers.forEach { lottoNumber ->
            lottoNumbers.add(lottoNumber.number)
        }
        println(lottoNumbers)
    }

    fun printLottoRankResult(lottoResult: HashMap<Rank, Int>) {
        println(STATISTICS + BAR)
        Rank.values().forEach { rank: Rank ->
            printLottoRankCount(rank, lottoResult)
        }
    }

    fun printLottoRankCount(rank: Rank, lottoResult: HashMap<Rank, Int>) {
        if (rank != Rank.MISS) println("${rank.countOfMatch}개 일치 (${rank.winningMoney}원) - ${lottoResult[rank] ?: 0} 개)")
    }

    fun printLottoProfitRate(profitRate: Double) {
        println("총 수익률은 ${String.format("%.2f", profitRate)}입니다.")
    }

    companion object {
        private const val ENTER_MONEY = "구입금액을 입력해 주세요."
        private const val PURCHASE_COUNT = "개를 구매했습니다."
        private const val ENTER_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val STATISTICS = "\n당첨 통계\n"
        private const val BAR = "---"
    }
}
