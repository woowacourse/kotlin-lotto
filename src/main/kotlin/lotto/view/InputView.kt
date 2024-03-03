package lotto.view

import lotto.model.Lotto
import lotto.model.LottoEvent
import lotto.model.manual.ManualEvent
import lotto.model.manual.ManualLottoCount
import lotto.model.user.UserInputVerifier
import lotto.model.wallet.Wallet
import lotto.model.wallet.WalletEvent
import lotto.model.winning.BonusEvent
import lotto.model.winning.BonusNumber

private const val REQUEST_PURCHASE_PRICE = "구입금액을 입력해 주세요."
private const val REQUEST_WINNING_NUM = "지난 주 당첨 번호를 입력해 주세요."
private const val REQUEST_BONUS_NUM = "보너스 볼을 입력해 주세요."
private const val REQUEST_MANUAL_NUM = "수동으로 구매할 로또 수를 입력해 주세요."
private const val REQUEST_LOTTO_NUM = "수동으로 구매할 번호를 입력해 주세요."

fun inputCostMessage(): Wallet {
    println(REQUEST_PURCHASE_PRICE)
    return when (val event = UserInputVerifier.inputCharge(readlnOrNull())) {
        is WalletEvent.Success -> event.wallet
        else -> {
            println(WalletEvent.checkWallet(event).message)
            inputCostMessage()
        }
    }
}

fun inputManualNumber(lottoCount: Int): ManualLottoCount {
    println(REQUEST_MANUAL_NUM)
    return when (val event = UserInputVerifier.inputManualCount(readlnOrNull(), lottoCount)) {
        is ManualEvent.Success -> event.manualLottoCount
        else -> {
            println(ManualEvent.checkManual(event).message)
            inputManualNumber(lottoCount)
        }
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
        is LottoEvent.Success -> event.lotto
        else -> {
            println(LottoEvent.checkLotto(event).message)
            inputLottoNumbers()
        }
    }
}

fun inputBonusNumber(winningLotto: Lotto): BonusNumber {
    println(REQUEST_BONUS_NUM)
    return when (val event = UserInputVerifier.inputBonus(readlnOrNull(), winningLotto)) {
        is BonusEvent.Success -> event.bonusNumber
        else -> {
            println(BonusEvent.checkBonusEvent(event).message)
            inputBonusNumber(winningLotto)
        }
    }
}
