package lotto.view

import lotto.model.LottoNumber
import lotto.model.LottoTicket
import lotto.model.Money

class ConsoleInputView : InputView {
    override fun getPurchasePrice(): Money {
        println(GET_PURCHASE_PRICE_MESSAGE)
        val money = readln().toLong()
        require(money > 0L)
        return Money(money)
    }

    override fun getManualCount(): Int {
        println(GET_MANUAL_COUNT_MESSAGE)
        return readln().toInt()
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
        val numbers =
            readln()
                .trim()
                .split(",")
                .map { LottoNumber.of(it.trim().toInt()) }
        return LottoTicket(numbers)
    }

    override fun getBonusNumber(): LottoNumber {
        println(GET_BONUS_NUMBER_MESSAGE)
        return LottoNumber.of(readln().toInt())
    }

    companion object {
        private const val GET_PURCHASE_PRICE_MESSAGE = "구입금액을 입력해 주세요."
        private const val GET_WINNING_TICKET_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val GET_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
        private const val GET_MANUAL_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val GET_MANUAL_TICKET_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."
    }
}
