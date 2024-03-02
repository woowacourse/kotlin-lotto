package lotto.view

class InputView {
    fun readPurchaseAmount(): Int? {
        println("구입금액을 입력해 주세요.")
        return readln().toIntOrNull()
    }

    fun readManualLottoAmount(): Int? {
        println("\n수동으로 구매할 로또 수를 입력해 주세요.")
        return readln().toIntOrNull()
    }

    fun readManualLottos(): List<String> {
        return readln().split(MANUAL_NUMBER_DELIMITER)
    }

    fun readWinningNumbers(): List<String> {
        println("\n지난 주 당첨 번호를 입력해 주세요.")
        return readln().split(WINNING_NUMBER_DELIMITER)
    }

    fun readBonusNumber(): Int? {
        println("보너스 볼을 입력해 주세요.")
        return readln().toIntOrNull()
    }

    companion object {
        private const val MANUAL_NUMBER_DELIMITER = ","
        private const val WINNING_NUMBER_DELIMITER = ","
    }
}
