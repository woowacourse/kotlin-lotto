package lotto.view

import lotto.domain.value.LottoPayInfo
import kotlin.runCatching

class InputView {
    fun readLottoPurchaseAmount(): Int {
        println(INPUT_PURCHASE_AMOUNT)
        return readSingleNumber()
    }

    fun readManualLottoQuantity(): Int {
        println(INPUT_MANUAL_LOTTO_QUANTITY)
        return readSingleNumber()
    }

    private fun readSingleNumber(): Int {
        val inputText = readln()
        return runCatching { inputText.toInt() }.getOrElse {
            throw IllegalArgumentException(
                CAN_NOT_TO_INT.format(
                    inputText,
                ),
            )
        }
    }

    fun readWinningLottoNumbersWithoutBonus(): List<Int> {
        println(INPUT_WINNING_LOTTO)
        return readLottoNumbers()
    }

    fun readManualLottoNumbers(payInfo: LottoPayInfo): List<List<Int>> {
        println(INPUT_MANUAL_LOTTO_NUMBER)
        return List(payInfo.manualLottoQuantity) { readLottoNumbers() }
    }

    private fun readLottoNumbers(): List<Int> {
        val inputMessage = readln()
        return runCatching {
            inputMessage.split(",").map { it.trim().toInt() }
        }.getOrElse {
            throw IllegalArgumentException(CAN_NOT_TO_INT.format(inputMessage))
        }
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
        private const val INPUT_MANUAL_LOTTO_QUANTITY = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MANUAL_LOTTO_NUMBER = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val INPUT_WINNING_LOTTO = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        private const val CAN_NOT_TO_INT = "정수로 변환할 수 없는 입력값입니다. (입력:%s)"
    }
}
