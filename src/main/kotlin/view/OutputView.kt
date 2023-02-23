package view

import domain.Rank.FIFTH
import domain.Rank.FIRST
import domain.Rank.FOURTH
import domain.Rank.SECOND
import domain.Rank.THIRD
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
        with(lottoResult) {
            println(Lotto_Result_Format.format(MATCH_THREE, FIFTH_REWARD, result[FIFTH] ?: NOTHING))
            println(Lotto_Result_Format.format(MATCH_FOUR, FOURTH_REWARD, result[FOURTH] ?: NOTHING))
            println(Lotto_Result_Format.format(MATCH_FIVE, THIRD_REWARD, result[THIRD] ?: NOTHING))
            println(Lotto_Result_Format.format(MATCH_FIVE, SECOND_REWARD, result[SECOND] ?: NOTHING))
            println(Lotto_Result_Format.format(MATCH_SIX, FIRST_REWARD, result[FIRST] ?: NOTHING))
        }
    }

    fun printLottoProfitRate(profitRate: Double) =
        println(PROFIT_RATE.format(String.format("%.2f", profitRate)))

    companion object {
        private const val Lotto_Result_Format = "%d개 일치 (%s원) - %d개"
        private const val ENTER_MONEY = "구입금액을 입력해 주세요."
        private const val PURCHASE_COUNT = "개를 구매했습니다."
        private const val ENTER_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val STATISTICS = "\n당첨 통계\n"
        private const val BAR = "---"
        private const val PROFIT_RATE = "총 수익률은 %s입니다."
        private const val NOTHING = 0
        private const val MATCH_THREE = 3
        private const val MATCH_FOUR = 4
        private const val MATCH_FIVE = 5
        private const val MATCH_SIX = 6
        private const val FIFTH_REWARD = "5000"
        private const val FOURTH_REWARD = "50000"
        private const val THIRD_REWARD = "1500000"
        private const val SECOND_REWARD = "30000000"
        private const val FIRST_REWARD = "2000000000"
    }
}
