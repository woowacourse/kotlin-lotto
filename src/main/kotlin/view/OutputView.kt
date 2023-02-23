package view

import domain.Rank
import model.Lotto
import model.LottoResult

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

    fun printLottoResult(lottoResult: LottoResult) {
        println(STATISTICS + BAR)
        println(
            "3개 일치 (5,000원) - ${if (lottoResult.result[Rank.FIFTH] == null) 0 else lottoResult.result[Rank.FIFTH]}개\n" +
                "4개 일치 (50,000원) - ${if (lottoResult.result[Rank.FOURTH] == null) 0 else lottoResult.result[Rank.FOURTH]}개\n" +
                "5개 일치 (1,500,000원) - ${if (lottoResult.result[Rank.THIRD] == null) 0 else lottoResult.result[Rank.THIRD]}개\n" +
                "5개 일치, 보너스 볼 일치 (30,000,000원) - ${if (lottoResult.result[Rank.SECOND] == null) 0 else lottoResult.result[Rank.SECOND]}개\n" +
                "6개 일치 (2,000,000,000원) - ${if (lottoResult.result[Rank.FIRST] == null) 0 else lottoResult.result[Rank.FIRST]}개",
        )
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
