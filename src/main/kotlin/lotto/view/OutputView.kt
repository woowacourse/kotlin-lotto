package lotto.view

class OutputView {
    fun printPurchaseLottoQuantity(
        passiveQuantity: Int,
        activeQuantity: Int,
    ) {
        println("수동으로 ${passiveQuantity}장, 자동으로 ${activeQuantity}개를 구매했습니다.")
    }

    fun printLottoNumbers(lottoNumbers: Set<Int>) {
        println(lottoNumbers)
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
        profitDescription: String,
    ) {
        println("총 수익률은 ${profitRate.formatToTwoDecimal()}입니다.(기준이 1이기 때문에 결과적으로 $profitDescription(이)라는 의미임)")
    }

    private fun Float.formatToTwoDecimal() = "%.2f".format(this)
}
