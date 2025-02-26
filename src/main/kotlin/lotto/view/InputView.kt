package lotto.view

import java.util.Scanner

class InputView {
    private val scanner = Scanner(System.`in`)

    fun readPurchaseAmount(): Int {
        println(READ_PURCHASE_AMOUNT_MESSAGE)
        return requireNotNull(scanner.nextLine().trim().toIntOrNull()) { INVALID_NUMBER_MESSAGE }
    }

    fun readManualLottoCount(): Int {
        println(READ_PASSIVITY_LOTTO_COUNT)
        return requireNotNull(scanner.nextLine().trim().toIntOrNull()) { INVALID_NUMBER_MESSAGE }
    }

    fun readLottoNumbers(): List<Int> {
        val lottoNumbersInput = scanner.nextLine().split(WINNING_NUMBERS_DELIMITER).map { it.trim() }
        return lottoNumbersInput.map { requireNotNull(it.toIntOrNull()) { INVALID_NUMBER_MESSAGE } }
    }

    fun readBonusNumber(): Int {
        val bonusNumberInput = scanner.nextLine().trim()
        return requireNotNull(bonusNumberInput.toIntOrNull()) { INVALID_NUMBER_MESSAGE }
    }

    private companion object {
        const val WINNING_NUMBERS_DELIMITER = ','
        const val READ_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요."
        const val READ_PASSIVITY_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        const val INVALID_NUMBER_MESSAGE = "숫자만 입력해 주세요.(공백 포함 x)"
    }
}
