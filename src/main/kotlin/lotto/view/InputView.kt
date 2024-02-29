package lotto.view

import lotto.model.BonusNumber
import lotto.model.Lotto
import lotto.model.user.UserEvent
import lotto.model.user.UserInputVerifier

private const val REQUEST_PURCHASE_PRICE = "구입금액을 입력해 주세요."
private const val REQUEST_WINNING_NUM = "지난 주 당첨 번호를 입력해 주세요."
private const val REQUEST_BONUS_NUM = "보너스 볼을 입력해 주세요."
private const val REQUEST_MANUAL_NUM = "수동으로 구매할 로또 수를 입력해 주세요."
private const val REQUEST_LOTTO_NUM = "수동으로 구매할 번호를 입력해 주세요."

fun inputCostMessage(): Int {
    println(REQUEST_PURCHASE_PRICE)
    return when (val event = UserInputVerifier.inputCharge(readlnOrNull())) {
        is UserEvent.PurchaseEvent.Success -> event.price
        else -> inputCostMessage()
    }
}

fun inputManualNumber(lottoCount: Int): Int {
    println(REQUEST_MANUAL_NUM)
    return when (val event = UserInputVerifier.inputManualCount(readlnOrNull(), lottoCount)) {
        is UserEvent.ManualEvent.Success -> event.count
        else -> inputManualNumber(lottoCount)
    }
}

fun inputManualLottoNumbers() {
    println(REQUEST_LOTTO_NUM)
}

fun inputWinningLottoNumbers() {
    println(REQUEST_WINNING_NUM)
}

fun inputLottoNumbers(): Lotto {
    return when (val event = UserInputVerifier.inputLottoNumbers(readlnOrNull())) {
        is UserEvent.LottoEvent.Success -> event.lotto
        else -> inputLottoNumbers()
    }
}

fun inputBonusNumber(winningLotto: Lotto): BonusNumber {
    println(REQUEST_BONUS_NUM)
    return when (val event = UserInputVerifier.inputBonus(readlnOrNull(), winningLotto)) {
        is UserEvent.BonusEvent.Success -> event.bonusNumber
        else -> inputBonusNumber(winningLotto)
    }
}

