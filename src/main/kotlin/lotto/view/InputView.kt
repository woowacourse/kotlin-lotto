package lotto.view

import lotto.constants.StringConstants.INPUT_BONUS_NUMBER
import lotto.constants.StringConstants.INPUT_PURCHASE_PRICE
import lotto.constants.StringConstants.INPUT_WINNING_LOTTO_NUMBERS
import lotto.constants.StringConstants.INVALID_BONUS_NUMBER
import lotto.constants.StringConstants.INVALID_LOTTO_NUMBER
import lotto.constants.StringConstants.LOTTO_NUMBER_DELIMITER
import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.PurchaseInfo

object InputView {

    fun readPurchasePrice(): PurchaseInfo {
        println(INPUT_PURCHASE_PRICE)
        val purchasePrice = readln()
        return PurchaseInfo(purchasePrice)
    }

    fun readWinningLottoNumbers(): Lotto {
        println(INPUT_WINNING_LOTTO_NUMBERS)
        val winningLottoNumbers = readln()
        validateDigit(winningLottoNumbers, INVALID_LOTTO_NUMBER)
        return Lotto(winningLottoNumbers.split(LOTTO_NUMBER_DELIMITER).map { it.toInt() })
    }

    fun readBonusNumber(): LottoNumber {
        println(INPUT_BONUS_NUMBER)
        val bonusNumber = readln()
        validateDigit(bonusNumber, INVALID_BONUS_NUMBER)
        return LottoNumber(bonusNumber.toInt())
    }

    private fun validateDigit(lottoNumbers: String, errorMessage: String) =
        require(lottoNumbers.split(LOTTO_NUMBER_DELIMITER).all { isDigit(it) }) {
            errorMessage
        }

    private fun isDigit(it: String) = it.toIntOrNull() != null

}
