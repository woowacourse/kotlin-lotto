package lotto.view

object InputView {
    private const val LOTTO_NUMBERS_DELIMITER = ","

    fun readPayment(): Int? {
        val paymentInput: String = readln()
        val payment: Int? = paymentInput.toIntOrNull()
        return payment
    }

    fun readManualQuantity(): Int? {
        val manualQuantityInput: String = readln()
        val manualQuantity: Int? = manualQuantityInput.toIntOrNull()
        return manualQuantity
    }

    fun readLottoNumbers(): List<Int>? {
        val lottoNumbersInput: String = readln()
        val lottoNumbers: List<Int> =
            lottoNumbersInput.split(LOTTO_NUMBERS_DELIMITER).map { number: String ->
                number.trim().toIntOrNull() ?: return null
            }
        return lottoNumbers
    }

    fun readBonusNumber(): Int? {
        val bonusNumberInput: String = readln()
        val bonusNumber: Int? = bonusNumberInput.toIntOrNull()
        return bonusNumber
    }
}
