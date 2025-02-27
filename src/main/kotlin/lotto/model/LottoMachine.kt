package lotto.model

// 로또 발행하는 머신
class LottoMachine {
    // 로또 티켓을 발행한다.
    fun issueLottoTickets(
        customerWantToBuyAutoLottoTicketCount: LottoTicketCount,
        manualLottoNumbers: List<List<Int>>,
    ): List<LottoTicket> =
        purchaseAutoLottoTickets(customerWantToBuyAutoLottoTicketCount) + purchaseManualLottoTickets(manualLottoNumbers)

    // 자동 로또를 발행
    private fun purchaseAutoLottoTickets(customerWantToBuyAutoLottoTicketCount: LottoTicketCount): List<LottoTicket> {
        val autoLottoGenerator = AutoLottoTicketGenerator()
        val autoLottoTickets =
            List(customerWantToBuyAutoLottoTicketCount.toInt()) { autoLottoGenerator.generateLottoTicket() }
        return autoLottoTickets
    }

    // 수동 로또를 발행
    private fun purchaseManualLottoTickets(manualLottoNumbers: List<List<Int>>): List<LottoTicket> {
        val manualLottoTickets = manualLottoNumbers.map { ManualLottoTicketGenerator(it).generateLottoTicket() }
        return manualLottoTickets
    }
}
