package lotto.domain

import lotto.generator.LottoManualGenerator
import lotto.generator.LottoRandomGenerator

class LottoMachine {
    fun createManualLottoTicket(input: List<Set<Int>>): List<Lotto> {
        val lottoTickets: MutableList<Lotto> = mutableListOf()
        input.forEach {
            lottoTickets.add(LottoManualGenerator(it).generateLottoNumbers())
        }
        return lottoTickets
    }

    fun createLottoTicket(
        purchaseAmount: Int,
        manualCount: Int,
    ): List<Lotto> {
        val autoCount = purchaseAmount - manualCount
        val lottoTickets: MutableList<Lotto> = mutableListOf()
        repeat(autoCount) {
            lottoTickets.add(createAutoLottoTicket())
        }
        return lottoTickets
    }

    private fun createAutoLottoTicket(): Lotto {
        return LottoRandomGenerator().generateLottoNumbers()
    }
}
