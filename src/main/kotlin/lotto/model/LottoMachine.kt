package lotto.model

class LottoMachine {
    fun issueLottoTickets(
        customerWantToBuyAutoLottoTicketCount: LottoTicketCount,
        manualLottoNumbers: List<List<Int>>,
    ): List<LottoTicket> = purchaseManualLottoTickets(manualLottoNumbers) + purchaseAutoLottoTickets(customerWantToBuyAutoLottoTicketCount)

    private fun purchaseAutoLottoTickets(customerWantToBuyAutoLottoTicketCount: LottoTicketCount): List<LottoTicket> {
        val autoLottoGenerator = AutoLottoTicketGenerator()
        val autoLottoTickets =
            List(customerWantToBuyAutoLottoTicketCount.toInt()) { autoLottoGenerator.generateLottoTicket() }
        return autoLottoTickets
    }

    private fun purchaseManualLottoTickets(manualLottoNumbers: List<List<Int>>): List<LottoTicket> {
        val manualLottoTickets = manualLottoNumbers.map { ManualLottoTicketGenerator(it).generateLottoTicket() }
        return manualLottoTickets
    }
}
