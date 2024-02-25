package lotto.view

import lotto.controller.inputBonus
import lotto.controller.inputCharge
import lotto.controller.inputWinning
import lotto.model.LottoNumber

const val REQUEST_PURCHASE_PRICE = "구입금액을 입력해 주세요."
const val REQUEST_WINNING_NUM = "당첨 번호를 입력해 주세요."
const val REQUEST_BONUS_NUM = "보너스 번호를 입력해 주세요."

fun inputCostMessage(): Int {
    println(REQUEST_PURCHASE_PRICE)
    return inputCharge(readlnOrNull()) ?: inputCostMessage()
}

fun inputWinNumbers(): LottoNumber {
    println(REQUEST_WINNING_NUM)
    return inputWinning(readlnOrNull()) ?: inputWinNumbers()
}

fun inputBonusNumber(): Int {
    println(REQUEST_BONUS_NUM)
    return inputBonus(readlnOrNull()) ?: inputBonusNumber()
}
