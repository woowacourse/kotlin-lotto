package view

import model.LottoNumber
import model.LottoTicket
import model.Money

interface InputView {
    fun getPurchasePrice(): Money

    fun getWinningTicket(): LottoTicket

    fun getBonusNumber(): LottoNumber
}
