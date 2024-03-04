package lotto.view

object InputView {
    private const val INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요."
    private const val INPUT_MANUAL_LOTTO_SIZE = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val INPUT_MANUAL_LOTTOS = "수동으로 구매할 번호를 입력해 주세요."
    private const val INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    private const val LOTTO_NUMBER_DELIMITER = ", "
    private const val INVALID_DIGIT = -1

    fun readPurchasePrice(): Int {
        println(INPUT_PURCHASE_PRICE)
        val inputPurchasePrice = readln()
        return inputPurchasePrice.validateAndConvertDigit()
    }

    fun readManualLottoSize(): Int {
        lineBreak()
        println(INPUT_MANUAL_LOTTO_SIZE)
        val inputManualLottoSize = readln()
        return inputManualLottoSize.validateAndConvertDigit()
    }

    fun readManualLottos(manualLottoSize: Int): List<List<Int>> {
        lineBreak()
        println(INPUT_MANUAL_LOTTOS)
        return List(manualLottoSize) { readLottoNumbers() }
    }

    fun readWinningLottoNumbers(): List<Int> {
        println(INPUT_WINNING_LOTTO_NUMBERS)
        return readLottoNumbers()
    }

    private fun readLottoNumbers(): List<Int> {
        val inputLottoNumbers = readln()
        return inputLottoNumbers.validateAndConvertDigitList()
    }

    fun readBonusNumber(): Int {
        println(INPUT_BONUS_NUMBER)
        val inputBonusNumber = readln()
        return inputBonusNumber.validateAndConvertDigit()
    }

    private fun String.validateAndConvertDigitList(): List<Int> {
        return split(LOTTO_NUMBER_DELIMITER).map { it.validateAndConvertDigit() }
    }

    private fun String.validateAndConvertDigit(): Int {
        return toIntOrNull() ?: INVALID_DIGIT
    }

    private fun lineBreak() = println()
}
