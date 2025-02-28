package lotto.domain

import lotto.generator.LottoGenerator

class LottoMachine {
    private val lottoTickets: MutableList<Lotto> = mutableListOf()

    fun buyLottoTicket(lottoGenerator: LottoGenerator) {
        lottoTickets.add(lottoGenerator.generateLottoNumbers())
    }

    fun getLottoTickets(): List<Lotto> {
        return lottoTickets.toList()
    }
}
