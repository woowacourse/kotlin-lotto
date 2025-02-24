package lotto.view

import lotto.model.ProfitStatus
import lotto.model.ProfitStatus.BREAK_EVEN
import lotto.model.ProfitStatus.LOSS
import lotto.model.ProfitStatus.PROFIT

class OutputView {
    fun printPurchaseAmountGuide() {
        println("구입금액을 입력해 주세요.")
    }

    fun printManualLottoQuantityGuide() {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
    }

    fun printManualLottoNumbersGuide() {
        println("수동으로 구매할 번호를 입력해 주세요.")
    }

    fun printPurchaseLottoQuantity(
        manualQuantity: Int,
        autoQuantity: Int,
    ) {
        println("수동으로 ${manualQuantity}장, 자동으로 ${autoQuantity}장을 구매했습니다.")
    }

    fun printLottoNumbers(lottoNumbers: List<Int>) {
        println(lottoNumbers)
    }

    fun printWinningNumbersGuide() {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
    }

    fun printBonusNumberGuide() {
        println("보너스 볼을 입력해 주세요.")
    }

    fun printWinningResultTitle() {
        println("당첨 통계")
        println("---------")
    }

    fun printWinningResult(
        requiredMatch: Int,
        profit: Int,
        countOfMatch: Int,
        matchBonus: Boolean,
    ) {
        print("${requiredMatch}개 일치${printMatchBonus(matchBonus)}")
        println("(${profit}원)- ${countOfMatch}개")
    }

    private fun printMatchBonus(matchBonus: Boolean): String = if (matchBonus) ", 보너스 볼 일치" else ""

    fun printProfitRate(
        profitRate: Float,
        profitStatus: ProfitStatus,
    ) {
        println("총 수익률은 ${profitRate.formatToTwoDecimal()}입니다. (기준이 1이기 때문에 결과적으로 ${getProfitMessage(profitStatus)}(이)라는 의미임)")
    }

    private fun Float.formatToTwoDecimal() = "%.2f".format(this)

    private fun getProfitMessage(profitStatus: ProfitStatus): String =
        when (profitStatus) {
            PROFIT -> "이득"
            LOSS -> "손해"
            BREAK_EVEN -> "본전"
        }
}
