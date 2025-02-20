package lotto.view

import lotto.constants.ErrorMessages
import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import kotlin.runCatching

class InputView {
    fun readPurchaseAmount(): PurchaseAmount {
        println(INPUT_PURCHASE_AMOUNT)
        val input = readln()
        val amount =
            runCatching {
                input.toInt()
            }.getOrElse { throw IllegalArgumentException(ErrorMessages.INVALID_NUMBER_FORMAT) }
        return PurchaseAmount(amount)
    }

    fun readWinningLottoNumbers(): Lotto {
        println(INPUT_WINNING_LOTTO)
        val input = readln()
        val lottoNumbers =
            runCatching {
                input.split(COMMA).map { LottoNumber(it.trim().toInt()) }
            }.getOrElse { throw IllegalArgumentException(ErrorMessages.INVALID_NUMBER_FORMAT) }
        return Lotto(lottoNumbers)
    }

    fun readBonusNumber(): LottoNumber {
        println(INPUT_BONUS_NUMBER)
        val input = readln()
        val bonusNumber =
            runCatching {
                input.toInt()
            }.getOrElse { throw IllegalArgumentException(ErrorMessages.INVALID_NUMBER_FORMAT) }
        return LottoNumber(bonusNumber)
    }

    companion object {
        private const val INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val INPUT_WINNING_LOTTO = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val COMMA = ","
    }
}
