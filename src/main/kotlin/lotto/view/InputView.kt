package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import kotlin.runCatching

class InputView {
    fun readPurchaseAmount(): PurchaseAmount {
        println(INPUT_PURCHASE_AMOUNT)
        val inputMessage = readln()
        runCatching { inputMessage.toInt() }.onFailure { throw IllegalArgumentException() }
        return PurchaseAmount(inputMessage.toInt())
    }

    fun readWinningLottoNumbers(): Lotto {
        println(INPUT_WINNING_LOTTO)
        val inputMessage = readln()
        val lotto =
            runCatching {
                Lotto(
                    inputMessage.split(",").map { LottoNumber(it.toInt()) },
                )
            }.getOrElse { throw IllegalArgumentException() }
        return lotto
    }

    fun readBonusNumber(): LottoNumber {
        println(INPUT_BONUS_NUMBER)
        val bonusNumber =
            runCatching {
                LottoNumber(readln().toInt())
            }.getOrElse { throw IllegalArgumentException() }
        return bonusNumber
    }

    companion object {
        private const val INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val INPUT_WINNING_LOTTO = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
