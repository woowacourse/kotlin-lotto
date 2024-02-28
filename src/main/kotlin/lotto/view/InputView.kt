package lotto.view

object InputView {
    const val INVALID_INPUT = -1

    fun readBudget(): Int {
        println("구입금액을 입력해 주세요.")
        return readlnOrNull()?.toIntOrNull() ?: INVALID_INPUT
    }

    fun readManualPurchaseCount(): Int {
        println("\n수동으로 구매할 로또 수를 입력해 주세요.")
        return readlnOrNull()?.toIntOrNull() ?: INVALID_INPUT
    }

    fun readManualPurchaseNumbers(manualPurchaseCount: Int): List<List<Int>> {
        println("\n수동으로 구매할 번호를 입력해 주세요.")
        return List(manualPurchaseCount) {
            val input = readlnOrNull().orEmpty()
            input.split(",").map { it.trim().toIntOrNull() ?: INVALID_INPUT }
        }
    }

    fun readWinningNumbers(): List<Int> {
        println("지난 주 당첨번호를 입력해 주세요.")
        val input = readlnOrNull().orEmpty()
        return input.split(",").map { it.trim().toIntOrNull() ?: INVALID_INPUT }
    }

    fun readBonusNumber(): Int {
        println("보너스 볼을 입력해 주세요.")
        return readlnOrNull()?.toIntOrNull() ?: INVALID_INPUT
    }
}
