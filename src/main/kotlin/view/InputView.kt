package view

class InputView : InputViewInterface {
    override fun inputPaymentMoney(): Int {
        println(INPUT_PAYMENT_MONEY)
        return readln().toIntOrNull() ?: return inputPaymentMoney()
    }

    override fun inputManualLottoCount(): Int {
        println(INPUT_MANUAL_LOTTO_COUNT)
        return readln().toIntOrNull() ?: return inputManualLottoCount()
    }

    override fun inputManualLottoNumbers(): List<Int> {
        println(INPUT_MANUAL_LOTTO_NUMBERS)
        val input = readlnOrNull() ?: return inputManualLottoNumbers()
        return input.split(DELIMITER).map { it.toIntOrNull() ?: return inputManualLottoNumbers() }
    }

    override fun inputWinningLottoNumbers(): List<Int> {
        println(INPUT_WINNING_LOTTO_NUMBERS)
        val input = readlnOrNull() ?: return inputWinningLottoNumbers()
        return input.split(DELIMITER).map { it.toIntOrNull() ?: return inputWinningLottoNumbers() }
    }

    override fun inputBonusNumber(): Int {
        println(INPUT_BONUS_NUMBER)
        return readln().toIntOrNull() ?: return inputBonusNumber()
    }

    companion object {
        private const val INPUT_PAYMENT_MONEY = "구입금액을 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
        private const val INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val DELIMITER = ","
    }
}
