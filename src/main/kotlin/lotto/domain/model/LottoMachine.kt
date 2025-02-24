package lotto.domain.model

import lotto.domain.value.LottoPayInfo

class LottoMachine {
    fun generateLottos(
        payInfo: LottoPayInfo,
        manualTicketsNumbers: List<List<Int>>? = null,
    ): Lottos {
        val manualTickets: List<Lotto> = manualTicketsNumbers?.map { Lotto.createManual(it.toSet()) } ?: listOf()
        val autoTickets: List<Lotto> = List(payInfo.autoLottoQuantity) { Lotto.createRandom() }
        return Lottos(manualTickets + autoTickets)
    }
}
