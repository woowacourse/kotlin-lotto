package view

import domain.lotto.Lotto
import domain.lotto.number.LottoNumber
import domain.money.Money
import util.validator.InputValidator

class InputView(
    private val inputValidator: InputValidator
) {
    fun inputPurchasingMoney(): Money {
        println(PURCHASING_MONEY_INPUT_MESSAGE)
        lateinit var result: Money
        kotlin.runCatching {
            inputValidator.validateNumeric(readln().trim())
        }.onSuccess {
            result = Money(it)
        }.onFailure {
            println(INPUT_REPEAT)
            return inputPurchasingMoney()
        }
        return result
    }

    fun inputManualLottoCount(): Int {
        println(PURCHASING_MANUAL_LOTTO_COUNT)
        var result: Int = 0
        kotlin.runCatching {
            inputValidator.validateNumeric(readln().trim())
        }.onSuccess {
            result = it
        }.onFailure {
            println(INPUT_REPEAT)
            return inputManualLottoCount()
        }
        return result
    }

    fun inputManualLottos(manualLottoCount: Int): List<Lotto> {
        println(PURCHASING_MANUAL_LOTTO)
        val manualLottos = mutableListOf<Lotto>()
        repeat(manualLottoCount) {
            val manualLotto = inputLottoNumbers() ?: return inputManualLottos(manualLottoCount)
            manualLottos.add(Lotto(manualLotto.map { LottoNumber(it.toInt()) }))
        }
        return manualLottos
    }

    fun inputLastWeekWinningNumbers(): List<Int> {
        println(LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE)
        return inputLottoNumbers() ?: inputLastWeekWinningNumbers()
    }

    private fun inputLottoNumbers(): List<Int>? {
        lateinit var result: List<Int>
        kotlin.runCatching {
            inputValidator.validateWinningNumbersIsNumeric(readln().split(WINNING_NUMBER_DELIMITER))
        }.onSuccess {
            result = it
        }.onFailure {
            println(INPUT_REPEAT)
            return null
        }
        return result
    }

    fun inputBonusBallNumber(): Int {
        println(BONUS_BALL_NUMBERS_INPUT_MESSAGE)
        var result: Int = 0
        kotlin.runCatching {
            inputValidator.validateNumeric(readln().trim())
        }.onSuccess {
            result = it
        }.onFailure {
            println(INPUT_REPEAT)
            return inputBonusBallNumber()
        }
        return result
    }

    companion object {
        private const val PURCHASING_MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요."
        private const val PURCHASING_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val PURCHASING_MANUAL_LOTTO = "수동으로 구매할 번호를 입력해 주세요."
        private const val LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_BALL_NUMBERS_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."
        private const val INPUT_REPEAT = "다시 입력해주세요."

        private const val WINNING_NUMBER_DELIMITER = ","
    }
}
