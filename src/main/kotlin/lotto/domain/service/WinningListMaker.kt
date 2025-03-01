package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto

class WinningListMaker {
    fun calculateRanks(
        winningLotto: WinningLotto,
        lottoList: List<Lotto>,
    ): List<Rank> = lottoList.map { winningLotto.findRank(it) }
}
