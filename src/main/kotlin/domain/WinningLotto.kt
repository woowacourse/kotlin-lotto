package domain

import domain.model.LottoDrawingResult
import domain.model.LottoNumber

data class WinningLotto(val lotto: Lotto, val bonus: LottoNumber) {
    init {
        require(lotto.contains(bonus).not()) { "보너스 번호인 ${bonus}와 당첨번호인 ${lotto.numbers}는 중복되면 안됩니다." }
    }

    fun countRank(lottoTickets: List<Lotto>): LottoDrawingResult {
        val rankCountMap = Rank.entries.associateWith { DEFAULT_COUNT }.toMutableMap()

        lottoTickets.forEach { targetLotto ->
            val rank = findRank(targetLotto)
            rankCountMap[rank] = rankCountMap.getOrDefault(rank, DEFAULT_COUNT) + COUNT_STEP
        }

        return LottoDrawingResult(rankCountMap)
    }

    private fun findRank(targetLotto: Lotto): Rank {
        val countOfMatch = (targetLotto.numbers intersect lotto.numbers).size
        val matchBonus = targetLotto.contains(bonus)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    companion object {
        private const val DEFAULT_COUNT = 0
        private const val COUNT_STEP = 1
    }
}
