package lotto.view

object InputView {
    const val INVALID_INPUT = -1

    fun readPrice(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readlnOrNull().orEmpty()
        val price = input.toIntOrNull() ?: INVALID_INPUT
        return price
    }

    fun readLottoWinningNumbers(): List<Int> {
        println("지난 주 당첨번호를 입력해 주세요.")
        val input = readlnOrNull().orEmpty()
        val winningNumbers = input.split(",").map { it.toIntOrNull() ?: INVALID_INPUT }
        return winningNumbers
    }

    fun readLottoBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        val input = readlnOrNull().orEmpty()
        val bonusNumber = input.toIntOrNull() ?: INVALID_INPUT
        return bonusNumber
    }
}
