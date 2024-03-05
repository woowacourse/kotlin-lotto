package lotto.view

import lotto.model.LottoNumber
import lotto.model.LottoTicket
import lotto.model.LottoTicketResult
import lotto.model.Money

class ConsoleInputView : InputView {
    override tailrec fun getPurchasePrice(): Money {
        println(GET_PURCHASE_PRICE_MESSAGE)
        val money: Long? = readln().toLongOrNull()
        if (money != null && money > 0L) {
            return Money(money)
        }
        println(INVALID_PURCHASE_PRICE_ERROR)
        return getPurchasePrice()
    }

    override tailrec fun getManualCount(): Int {
        println(GET_MANUAL_COUNT_MESSAGE)
        val manualCount: Int? = readln().toIntOrNull()
        if (manualCount != null) {
            return manualCount
        }
        println(INVALID_MANUAL_COUNT_ERROR)
        return getManualCount()
    }

    override fun getManualLottoTickets(count: Int): List<LottoTicket> {
        println(GET_MANUAL_TICKET_MESSAGE)
        return List(count) { getLottoTicket() }
    }

    override fun getWinningTicket(): LottoTicket {
        println(GET_WINNING_TICKET_MESSAGE)
        return getLottoTicket()
    }

    private tailrec fun getLottoTicket(): LottoTicket {
        val intNumbers =
            readln()
                .trim()
                .split(",")
                .map { it.trim().toIntOrNull() }
        val numbers = intNumbers.map { LottoNumber.getOrNull(it) }
        when (val result = LottoTicket.fromListToResult(numbers)) {
            is LottoTicketResult.Success -> return result.lottoTicket
            is LottoTicketResult.ContainsNullLottoNumberFailure -> println(INVALID_LOTTO_TICKET_NUMBER)
            is LottoTicketResult.IsInvalidSizeOrDuplicated -> println(INVALID_LOTTO_TICKET_SIZE_OR_DUPLICATED)
        }
        return getLottoTicket()
    }

    override tailrec fun getBonusNumber(): LottoNumber {
        println(GET_BONUS_NUMBER_MESSAGE)
        val bonusNumber = readln().toIntOrNull()
        val result = LottoNumber.getOrNull(bonusNumber)
        if (result != null) {
            return result
        }
        println(INVALID_BONUS_NUMBER)
        return getBonusNumber()
    }

    companion object {
        private const val GET_PURCHASE_PRICE_MESSAGE = "구입금액을 입력해 주세요."
        private const val GET_WINNING_TICKET_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        private const val GET_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
        private const val GET_MANUAL_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val GET_MANUAL_TICKET_MESSAGE = "수동으로 구매할 번호를 입력해 주세요."

        private const val INVALID_PURCHASE_PRICE_ERROR = "구입 금액을 잘못 입력하셨습니다."
        private const val INVALID_MANUAL_COUNT_ERROR = "횟수를 잘못 입력하셨습니다."
        private const val INVALID_LOTTO_TICKET_NUMBER = "로또 번호를 잘못 입력하셨습니다."
        private const val INVALID_LOTTO_TICKET_SIZE_OR_DUPLICATED = "로또 번호를 모자르게 입력했거나 중복되었습니다."
        private const val INVALID_BONUS_NUMBER = "보너스 번호를 잘못 입력하셨습니다."
    }
}
