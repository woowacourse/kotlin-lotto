package lotto.domain.model

import lotto.domain.value.LottoPayInfo

class LottoMachine {
    fun generateLottos(
        payInfo: LottoPayInfo,
        manualTicketsNumbers: List<List<Int>> = listOf(listOf()),
    ): Lottos {
        val manualTickets: List<Lotto> =
            if (payInfo.manualLottoQuantity > 0) manualTicketsNumbers.map { Lotto.createManual(it.toSet()) } else listOf()
        val autoTickets: List<Lotto> = List(payInfo.autoLottoQuantity) { Lotto.createRandom() }
        return Lottos(manualTickets + autoTickets)
    }
}
