package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.model.WinningLotto
import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import kotlin.runCatching

class InputView {
    fun readPurchaseAmount(): PurchaseAmount {
        println(INPUT_PURCHASE_AMOUNT)
        val inputText = readln()
        val purchaseAmount =
            runCatching { PurchaseAmount(inputText.toInt()) }.getOrElse {
                throw IllegalArgumentException(
                    CAN_NOT_TO_INT.format(
                        inputText,
                    ),
                )
            }
        return purchaseAmount
    }

    fun readWinningLottoWithoutBonusNumber(): Lotto {
        println(INPUT_WINNING_LOTTO)
        val inputMessage = readln()
        val lotto =
            run {
                Lotto(
                    inputMessage.split(",").map { LottoNumber(it.trim().toInt()) }.toSet(),
                )
            }

        return lotto
    }

    fun getWinningLottoWithBonusNumber(lotto: Lotto): WinningLotto {
        println(INPUT_BONUS_NUMBER)
        val winningLotto =
            run {
                WinningLotto(
                    lotto,
                    LottoNumber(readln().toInt()),
                )
            }
        return winningLotto
    }

    companion object {
        private const val INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val INPUT_WINNING_LOTTO = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        private const val CAN_NOT_TO_INT = "정수로 변환할 수 없는 입력값입니다. (입력:%s)"
    }
}
