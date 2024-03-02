package view

class InputView {
    fun readPurchaseAmount(): Long {
        println(GUIDE_INPUT_PURCHASE_AMOUNT)
        val number = readln().toLongOrNull()
        requireNotNull(number)
        return number
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

    private fun readLotteryNumbers(): List<Int> {
        val input = readln()
        require(input.isNotBlank()) { "공백을 입력하셨습니다." }
        return input.toIntList()
    }

    fun readBonusNumber(): String {
        println(GUIDE_INPUT_BONUS_NUMBER)
        return readln()
    }

    companion object {
        private const val GUIDE_INPUT_PURCHASE_AMOUNT = "구입 금액을 입력해주세요."
        private const val GUIDE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val GUIDE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}

private fun String.toIntList(): List<Int> =
    this.split(",")
        .map { it.trim().toIntOrNull() ?: throw IllegalArgumentException("로또 번호 입력 형식이 올바르지 않습니다.") }
