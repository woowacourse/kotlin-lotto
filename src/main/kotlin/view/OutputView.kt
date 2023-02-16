package view

import domain.Rank
import model.Lotto
import java.text.DecimalFormat

class OutputView {

    fun printInputMoney() = println(ENTER_MONEY)
    fun printLottoCount(count: Int) = println("$count" + PURCHASE_COUNT)
    fun printInputWinningNumbers() = println(ENTER_WINNING_NUMBER)
    fun printInputBonusNumber() = println(ENTER_BONUS_NUMBER)

    fun printLotto(lotto: Lotto) {
        println(lotto.lottoNumbers)
    }

    fun printLottoResult(lottoResult: HashMap<Rank, Int>) {
        println(STATISTICS + BAR)
        println(
            "3개 일치 (5,000원) - ${if (lottoResult[Rank.FIFTH] == null) 0 else lottoResult[Rank.FIFTH]}개\n" +
                "4개 일치 (50,000원) - ${if (lottoResult[Rank.FOURTH] == null) 0 else lottoResult[Rank.FOURTH]}개\n" +
                "5개 일치 (1,500,000원) - ${if (lottoResult[Rank.THIRD] == null) 0 else lottoResult[Rank.THIRD]}개\n" +
                "5개 일치, 보너스 볼 일치 (30,000,000원) - ${if (lottoResult[Rank.SECOND] == null) 0 else lottoResult[Rank.SECOND]}개\n" +
                "6개 일치 (2,000,000,000원) - ${if (lottoResult[Rank.FIRST] == null) 0 else lottoResult[Rank.FIRST]}개",
        )
    }

    fun printLottoProfitRate(profitRate: Double) {
        val formattingNum = DecimalFormat("##.00")

        println("총 수익률은 ${formattingNum.format(profitRate)}입니다.")
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
