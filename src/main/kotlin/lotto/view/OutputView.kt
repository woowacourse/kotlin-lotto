package lotto.view

class OutputView {
    fun printPurchaseAmountGuide() {
        println("구입금액을 입력해 주세요.")
    }

    fun printPurchaseLottoQuantity(quantity: Int) {
        println("${quantity}개를 구매했습니다.")
    }

    fun printLottoNumbers(lottoNumbers: List<Int>) {
        println(lottoNumbers)
    }

    fun printLastWeekWinningNumbersGuide() {
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

    fun printProfitRate(profitRate: Float) {
        println("총 수익률은 ${profitRate}입니다.")
    }
}
