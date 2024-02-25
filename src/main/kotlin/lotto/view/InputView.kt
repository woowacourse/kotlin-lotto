package lotto.view

object InputView {
    const val INVALID_INPUT = -1

    fun reedBuyLottoPrice(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readlnOrNull().orEmpty()
        return input.toIntOrNull() ?: INVALID_INPUT
    }

    fun readManualBuyLottoCount(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        val input = readlnOrNull().orEmpty()
        return input.toIntOrNull() ?: INVALID_INPUT
    }

    fun readManualLottoBuyNumbers(): List<Int> {
        println("수동으로 구매할 번호를 입력해 주세요.")
        val input = readlnOrNull().orEmpty()
        return input.split(",").map { it.toIntOrNull() ?: INVALID_INPUT }
    }

    fun readLottoWinningNumbers(): List<Int> {
        println("지난 주 당첨번호를 입력해 주세요.")
        val input = readlnOrNull().orEmpty()
        return input.split(",").map { it.toIntOrNull() ?: INVALID_INPUT }
    }

    fun readLottoBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        val input = readlnOrNull().orEmpty()
        return input.toIntOrNull() ?: INVALID_INPUT
    }
}
