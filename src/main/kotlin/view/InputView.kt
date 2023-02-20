package view

import domain.lotto.number.LottoNumber
import domain.lotto.size.LottoSize
import domain.money.Money

class InputView {
    fun inputPurchasingMoney(): Money {
        println(PURCHASING_MONEY_INPUT_MESSAGE)
        return Money.from(readln().trim())
    }

    fun inputPurchasingManualLottoSize(): LottoSize {
        println()
        println(PURCHASE_MANUAL_LOTTO_COUNT_INPUT_MESSAGE)
        return LottoSize.from(readln().trim())
    }

    fun inputLastWeekWinningNumbers(): List<String> {
        println(LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE)
        return readln().split(WINNING_NUMBER_DELIMITER)
    }

    fun inputBonusBallNumber(): LottoNumber {
        println(BONUS_BALL_NUMBERS_INPUT_MESSAGE)
        return LottoNumber.from(readln().trim())
    }

    companion object {
        private const val PURCHASING_MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요."
        private const val PURCHASE_MANUAL_LOTTO_COUNT_INPUT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val LAST_WEEK_WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_BALL_NUMBERS_INPUT_MESSAGE = "보너스 볼을 입력해 주세요."

        private const val WINNING_NUMBER_DELIMITER = ","
    }
}
