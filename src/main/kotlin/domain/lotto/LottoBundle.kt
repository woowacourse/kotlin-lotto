package domain.lotto

import domain.Rank
import domain.WinningNumbers

class LottoBundle(value: List<Lotto>) {
    private val _lottos = mutableListOf<Lotto>()
        .apply { addAll(value) }
    val lottos: List<Lotto> get() = _lottos
    val size: Int get() = lottos.size
    fun compareWithWinningNumbers(winningNumbers: WinningNumbers): List<Rank> {
        return lottos.map { winningNumbers.compareLotto(it) }
    }

    fun add(value: LottoBundle) {
        _lottos.addAll(value.lottos)
    }

    fun add(value: List<Lotto>) {
        _lottos.addAll(value)
    }
}
