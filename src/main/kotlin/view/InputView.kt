package view

import domain.model.Lotto
import domain.model.LottoNumber
import validator.NumericValidator

class InputView {
    fun readPurchasePrice(): String {
        println(MESSAGE_INPUT_PURCHASE_PRICE)
        return readln().also { NumericValidator(it) }
    }

    fun readWinningNumbers(): List<String> {
        println(MESSAGE_INPUT_WINNING_NUMBER)
        val lotto = readln().split(",").map { it.trim().also { lottoNumber -> NumericValidator(lottoNumber) } }
        return lotto
    }

    fun readBonusNumber(): String {
        println(MESSAGE_INPUT_BONUS_NUMBER)
        return readln().also { NumericValidator(it) }
    }

    fun readManualLottoAmount(): String {
        println(MESSAGE_INPUT_MANUAL_LOTTO_AMOUNT)
        return readln().also { NumericValidator(it) }
    }

    fun askForManualLottoNumber() {
        println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBER)
    }

    fun readManualLottoNumber(): Lotto {
        val manualLottoNumbers = readln().split(",").map { it.trim().also { lottoNumber -> NumericValidator(lottoNumber) } }
        return Lotto(manualLottoNumbers.map { LottoNumber(it.toInt()) })
    }

    companion object {
        const val MESSAGE_INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요."
        const val MESSAGE_INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요."
        const val MESSAGE_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        const val MESSAGE_INPUT_MANUAL_LOTTO_AMOUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        const val MESSAGE_INPUT_MANUAL_LOTTO_NUMBER = "수동으로 구매할 번호를 입력해 주세요."
    }
}
