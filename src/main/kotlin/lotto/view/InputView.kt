package lotto.view

import lotto.constants.StringConstants.INPUT_BONUS_NUMBER
import lotto.constants.StringConstants.INPUT_PURCHASE_PRICE
import lotto.constants.StringConstants.INPUT_WINNING_LOTTO_NUMBERS
import lotto.constants.StringConstants.INVALID_BONUS_NUMBER
import lotto.constants.StringConstants.INVALID_LOTTO_NUMBER
import lotto.constants.StringConstants.INVALID_PURCHASE_PRICE
import lotto.constants.StringConstants.LOTTO_NUMBER_DELIMITER
import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.PurchaseInfo

object InputView {
    fun readPurchasePrice(): PurchaseInfo {
        println(INPUT_PURCHASE_PRICE)
        val inputPurchasePrice = readln()
        val purchasePrice = inputPurchasePrice.validateAndConvertDigit(INVALID_PURCHASE_PRICE)
        return PurchaseInfo(purchasePrice)
    }

    fun readWinningLottoNumbers(): Lotto {
        println(INPUT_WINNING_LOTTO_NUMBERS)
        val inputWinningLottoNumbers = readln()
        val winningLottoNumbers = inputWinningLottoNumbers.validateAndConvertDigitList(INVALID_LOTTO_NUMBER)
        return Lotto(winningLottoNumbers)
    }

    fun readBonusNumber(): LottoNumber {
        println(INPUT_BONUS_NUMBER)
        val inputBonusNumber = readln()
        val bonusNumber = inputBonusNumber.validateAndConvertDigit(INVALID_BONUS_NUMBER)
        return LottoNumber(bonusNumber)
    }

    private fun String.validateAndConvertDigitList(errorMessage: String): List<Int> {
        return split(LOTTO_NUMBER_DELIMITER).map { it.validateAndConvertDigit(errorMessage) }
    }

    private fun String.validateAndConvertDigit(errorMessage: String): Int {
        return toIntOrNull() ?: throw IllegalArgumentException(errorMessage)
    }
}
