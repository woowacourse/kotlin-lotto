package lotto.view

class OutputView {
    fun printPurchaseAmountGuide() {
        println(PURCHASE_AMOUNT_GUIDE_MESSAGE)
    }

    fun printLottoCount(lottoCount: Int) {
        println(LOTTO_COUNT_MESSAGE_FORMAT.format(lottoCount))
    }

    fun printWinningLottoNumbersGuide() {
        println(WINNING_LOTTO_NUMBERS_GUIDE_MESSAGE)
    }

    fun printBonusNumberGuide() {
        println(BONUS_NUMBER_GUIDE_MESSAGE)
    }

    fun printErrorMessage(message: String) {
        println(ERROR_FORMAT.format(message))
    }

    companion object {
        private const val PURCHASE_AMOUNT_GUIDE_MESSAGE = "구입금액을 입력해 주세요."
        private const val LOTTO_COUNT_MESSAGE_FORMAT = "%d개를 구매했습니다."
        private const val WINNING_LOTTO_NUMBERS_GUIDE_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_NUMBER_GUIDE_MESSAGE = "보너스 볼을 입력해 주세요."
        private const val ERROR_FORMAT = "[ERROR] %s"
    }
}
