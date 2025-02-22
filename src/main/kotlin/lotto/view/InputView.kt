package lotto.view

import kotlin.runCatching

class InputView {
    fun readLottoPurchaseAmount(): Int {
        println(INPUT_PURCHASE_AMOUNT)
        val inputText = readln()
        val lottoPurchaseAmount =
            runCatching { inputText.toInt() }.getOrElse {
                throw IllegalArgumentException(
                    CAN_NOT_TO_INT.format(
                        inputText,
                    ),
                )
            }
        return lottoPurchaseAmount
    }

    fun readWinningLottoNumbersWithoutBonus(): List<Int> {
        println(INPUT_WINNING_LOTTO)
        val inputMessage = readln()
        val winningLottoNumbers =
            runCatching {
                inputMessage.split(",").map { it.trim().toInt() }
            }.getOrElse {
                throw IllegalArgumentException(CAN_NOT_TO_INT.format(inputMessage))
            }
        return winningLottoNumbers
    }

    fun readBonusNumber(): Int {
        println(INPUT_BONUS_NUMBER)
        val inputMessage = readln()
        val bonusNumber =
            runCatching {
                inputMessage.trim().toInt()
            }.getOrElse {
                throw IllegalArgumentException(CAN_NOT_TO_INT.format(inputMessage))
            }
        return bonusNumber
    }

    companion object {
        private const val INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요."
        private const val INPUT_WINNING_LOTTO = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        private const val CAN_NOT_TO_INT = "정수로 변환할 수 없는 입력값입니다. (입력:%s)"
    }
}
