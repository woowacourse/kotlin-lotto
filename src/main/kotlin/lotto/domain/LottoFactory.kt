package lotto.domain

class LottoFactory(private val generateStrategy: GenerateStrategy) {
    fun publishLottoTickets(purchaseAmount: PurchaseAmount): LottoTickets {
        val lottoTickets: MutableList<LottoTicket> = mutableListOf()
        while (purchaseAmount.isUnderCountOfLottoTicket(lottoTickets.size)) {
            putIfNotPresent(lottoTickets)
        }
        return LottoTickets(lottoTickets)
    }

    private fun putIfNotPresent(lottoTickets: MutableList<LottoTicket>) {
        val lottoTicket: LottoTicket = generate()
        if (!lottoTickets.contains(lottoTicket)) {
            lottoTickets.add(lottoTicket)
        }
    }

    private fun generate(): LottoTicket {
        return generateStrategy.generate()
    }
}
