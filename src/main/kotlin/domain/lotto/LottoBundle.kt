package domain.lotto

import domain.Rank
import domain.WinningNumbers

class LottoBundle(val lottos: List<Lotto>) {
    fun compareWithWinningNumbers(winningNumbers: WinningNumbers): List<Rank> {
        return lottos.map { winningNumbers.compareLotto(it) }
    }
}
