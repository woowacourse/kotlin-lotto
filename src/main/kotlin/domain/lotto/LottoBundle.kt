package domain.lotto

import domain.Rank
import domain.WinningNumbers

class LottoBundle(val lottos: List<Lotto>) {
    val size: Int get() = lottos.size

    fun compareWithWinningNumbers(winningNumbers: WinningNumbers): List<Rank> {
        return lottos.map { winningNumbers.compareLotto(it) }
    }

    operator fun plus(otherBundle: LottoBundle): LottoBundle {
        return LottoBundle(lottos + otherBundle.lottos)
    }
}
