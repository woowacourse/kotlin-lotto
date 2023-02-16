package lotto.view

object OutputView {
    private const val GET_PURCHASE_MONEY_SCRIPT = "구입금액을 입력해 주세요."
    private const val GET_MAIN_LOTTO_NUMBERS_SCRIPT = "지난 주 당첨 번호를 입력해 주세요."
    private const val GET_BONUS_LOTTO_NUMBER_SCRIPT = "보너스 볼을 입력해 주세요."
    private const val WINNING_STATS_SCRIPT = "당첨 통계\n---------"

    fun printGetPurchaseMoneyScript() {
        println(GET_PURCHASE_MONEY_SCRIPT)
    }

    fun printGetMainLottoNumbersScript() {
        println(GET_MAIN_LOTTO_NUMBERS_SCRIPT)
    }

    fun printGetBonusLottoNumberScript() {
        println(GET_BONUS_LOTTO_NUMBER_SCRIPT)
    }

    fun printWinningStatsScript() {
        println(WINNING_STATS_SCRIPT)
    }
}
