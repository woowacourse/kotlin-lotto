package view

class InputView {
    fun readPurchaseAmount(): Long {
        println(GUIDE_INPUT_PURCHASE_AMOUNT)
        return readln().toLongOrInvalidFormat()
    }

    fun readManualLotteryQuantity(): String {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        return readln()
    }

    fun guideManualLottery() = println("수동으로 구매할 번호를 입력해 주세요.")

    fun readManualLottery(): List<Int> = readLotteryNumbers()

    fun readWinningLottery(): List<Int> {
        println(GUIDE_INPUT_WINNING_NUMBERS)
        return readLotteryNumbers()
    }

    fun readBonusNumber(): Int {
        println(GUIDE_INPUT_BONUS_NUMBER)
        return readNotBlankInput().toIntOrInvalidFormat()
    }

    private fun readLotteryNumbers(): List<Int> = readNotBlankInput().toIntList()

    private fun readNotBlankInput(): String {
        val input = readln()
        require(input.isNotBlank()) { "공백을 입력하셨습니다." }
        return input
    }

    companion object {
        private const val GUIDE_INPUT_PURCHASE_AMOUNT = "구입 금액을 입력해주세요."
        private const val GUIDE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val GUIDE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val INVALID_NUMBER_FORMAT = "입력 형식이 올바르지 않습니다. 숫자만 입력하세요"

        private fun String.toIntList(): List<Int> =
            this.split(",")
                .map { it.trim().toIntOrInvalidFormat() }

        private fun String.toIntOrInvalidFormat(): Int = this.toIntOrNull() ?: throw IllegalArgumentException(INVALID_NUMBER_FORMAT)

        private fun String.toLongOrInvalidFormat(): Long = this.toLongOrNull() ?: throw IllegalArgumentException(INVALID_NUMBER_FORMAT)
    }
}
