package view

import model.LottoNumber
import model.LottoTicket
import model.Money

interface InputView {
    fun getPurchasePrice(): Money

    fun getManualCount(): Int

    fun getManualLottoTickets(count: Int): List<LottoTicket>

    fun getWinningTicket(): LottoTicket

    fun getBonusNumber(): LottoNumber
}
