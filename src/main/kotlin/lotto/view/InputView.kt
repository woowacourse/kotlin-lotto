package lotto.view

import lotto.model.LottoNumber
import lotto.model.LottoTicket
import lotto.model.Money

interface InputView {
    fun getPurchasePrice(): Money

    fun getManualCount(): Int

    fun getManualLottoTickets(count: Int): List<LottoTicket>

    fun getWinningTicket(): LottoTicket

    fun getBonusNumber(): LottoNumber
}
