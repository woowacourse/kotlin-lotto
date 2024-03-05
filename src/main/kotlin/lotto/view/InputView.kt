package lotto.view

object InputView {
    private const val INVALID_INPUT_VALUE = -1

    fun getPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readln()
        return input.toIntOrNull() ?: INVALID_INPUT_VALUE
    }

    fun getWinningNumbers(): Set<Int> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        val input = readln()
        return input.split(",").map { it.trim().toIntOrNull() ?: INVALID_INPUT_VALUE }.toSet()
    }

    fun getBonusNumber(): Int {
        println("보너스 번호를 입력해 주세요.")
        val input = readln()
        return input.toIntOrNull() ?: INVALID_INPUT_VALUE
    }

    fun getNumberOfManualLotto(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        return readln().toIntOrNull() ?: INVALID_INPUT_VALUE
    }

    fun getManualLottoNumbers(count: Int): List<Set<Int>> {
        println("수동으로 구매할 번호를 입력해 주세요.")
        return List(count) {
            val input = readln().split(",")
            input.map { it.trim().toIntOrNull() ?: INVALID_INPUT_VALUE }.toSet()
        }
    }
}
