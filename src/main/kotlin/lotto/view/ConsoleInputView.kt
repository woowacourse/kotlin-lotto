package lotto.view

import lotto.model.LottoNumber
import lotto.model.LottoTicket
import lotto.model.Money

class ConsoleInputView : InputView {
    override fun getPurchasePrice(): Money {
        println(GET_PURCHASE_PRICE_MESSAGE)
        val money: Long =
            repeatUntilGetValidInput(INVALID_PRICE_ERROR) {
                readln().toLongOrNull()
            }
        require(money > 0L)
        return Money(money)
    }

    override fun getManualCount(): Int {
        println(GET_MANUAL_COUNT_MESSAGE)
        val manualCount: Int =
            repeatUntilGetValidInput(INVALID_MANUAL_COUNT_ERROR) {
                readln().toIntOrNull()
            }
        return manualCount
    }

    override fun getManualLottoTickets(count: Int): List<LottoTicket> {
        println(GET_MANUAL_TICKET_MESSAGE)
        return List(count) { getLottoTicket() }
    }

    override fun getWinningTicket(): LottoTicket {
        println(GET_WINNING_TICKET_MESSAGE)
        return getLottoTicket()
    }

    private fun getLottoTicket(): LottoTicket {
        val intNumbers =
            repeatUntilGetValidInputList(INVALID_LOTTO_TICKET_NUMBER) {
                readln()
                    .trim()
                    .split(",")
                    .map { it.trim().toIntOrNull() }
            }
        val numbers = intNumbers.map { LottoNumber.of(it) }
        return LottoTicket(numbers)
    }

    override fun getBonusNumber(): LottoNumber {
        println(GET_BONUS_NUMBER_MESSAGE)
        val bonusNumber: Int =
            repeatUntilGetValidInput(INVALID_BONUS_NUMBER) {
                readln().toIntOrNull()
            }
        return LottoNumber.of(bonusNumber)
    }

    private fun <T> repeatUntilGetValidInput(
        errorMsg: String,
        inputFunction: () -> T?,
    ): T {
        var result: T?
        do {
            result = inputFunction()
            if (result == null) {
                println(errorMsg)
            }
        } while (result == null)
        return result
    }

    private fun <T> repeatUntilGetValidInputList(
        errorMsg: String,
        inputFunction: () -> List<T?>,
    ): List<T> {
        var result: List<T?>
        do {
            result = inputFunction()
            val condition = result.any { it == null }
            if (condition) {
                println(errorMsg)
            }
        } while (condition)
        return result.filterNotNull()
    }

    companion object {
        private const val GET_PURCHASE_PRICE_MESSAGE = "구입금액을 입력해 주세요."
        private const val GET_WINNING_TICKET_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val GET_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
        private const val GET_MANUAL_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val GET_MANUAL_TICKET_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."

        private const val INVALID_PRICE_ERROR = "갸격을 잘못 입력하셨습니다."
        private const val INVALID_MANUAL_COUNT_ERROR = "횟수를 잘못 입력하셨습니다."
        private const val INVALID_LOTTO_TICKET_NUMBER = "로또 번호를 잘못 입력하셨습니다."
        private const val INVALID_BONUS_NUMBER = "보너스 번호를 잘못 입력하셨습니다."
    }
}
