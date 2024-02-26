package lotto.controller

import lotto.controller.Verifier.inputBonusNumber
import lotto.controller.Verifier.inputCharge
import lotto.controller.Verifier.inputWinning
import lotto.model.Lotto

fun readCharge(): Int {
    return inputCharge(readlnOrNull()) ?: readCharge()
}

fun readLottoNumber(): Lotto {
    return inputWinning(readlnOrNull()) ?: readLottoNumber()
}

fun readLottoBonusNumber(): Int {
    return inputBonusNumber(readlnOrNull()) ?: readLottoBonusNumber()
}
