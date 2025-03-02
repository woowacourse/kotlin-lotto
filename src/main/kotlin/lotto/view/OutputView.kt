package lotto.view

import lotto.domain.model.LottoTicket
import lotto.domain.valueobject.ObjectQuantity

class OutputView {
    fun showParagraphSeparation() {
        println()
    }

    fun showBoughtLottoQuantity(
        manualQuantity: ObjectQuantity,
        autoQuantity: ObjectQuantity,
    ) {
        println(ALERT_BOUGHT_LOTTO_QUANTITY.format(manualQuantity.quantity, autoQuantity.quantity))
    }

    fun showBoughtLottoTickets(lottoTickets: List<LottoTicket>) {
        lottoTickets.forEach { ticket ->
            println(ticket.getSortedLottoNumbers())
        }
        showParagraphSeparation()
    }

    companion object {
        private const val ALERT_BOUGHT_LOTTO_QUANTITY = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
    }
}
