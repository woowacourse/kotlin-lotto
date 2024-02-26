package lotto.view

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.PurchaseOrder

object InputView {
    private const val INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요."
    private const val INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    private const val LOTTO_NUMBER_DELIMITER = ", "
    private const val INVALID_DIGIT = "숫자만 입력해 주세요."

    fun readPurchasePrice(): PurchaseOrder {
        println(INPUT_PURCHASE_PRICE)
        val inputPurchasePrice = readln()
        val purchasePrice = inputPurchasePrice.validateAndConvertDigit()
        return PurchaseOrder(purchasePrice)
    }

    fun readWinningLottoNumbers(): Lotto {
        println(INPUT_WINNING_LOTTO_NUMBERS)
        val inputWinningLottoNumbers = readln()
        val winningLottoNumbers = inputWinningLottoNumbers.validateAndConvertDigitList()
        return Lotto.create(winningLottoNumbers)
    }

    fun readBonusNumber(): LottoNumber {
        println(INPUT_BONUS_NUMBER)
        val inputBonusNumber = readln()
        val bonusNumber = inputBonusNumber.validateAndConvertDigit()
        return LottoNumber(bonusNumber)
    }

    private fun String.validateAndConvertDigitList(): List<Int> {
        return split(LOTTO_NUMBER_DELIMITER).map { it.validateAndConvertDigit() }
    }

    private fun String.validateAndConvertDigit(): Int {
        return toIntOrNull() ?: throw IllegalArgumentException(INVALID_DIGIT)
    }
}
