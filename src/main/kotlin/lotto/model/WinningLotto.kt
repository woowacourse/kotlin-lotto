package lotto.model

data class WinningLotto(val lotto: Lotto, val bonus: LottoNumber) {
    init {
        require(lotto.contains(bonus).not()) { "보너스 번호인 ${bonus.value}와 당첨번호인 ${lotto}는 중복되면 안됩니다." }
    }

    fun countRank(lottoTickets: List<Lotto>): LottoDrawingResult {
        val rankCountMap = Rank.entries.associateWith { DEFAULT_COUNT }.toMutableMap()

        lottoTickets.forEach { targetLotto ->
            val countOfMatch = targetLotto.countMatch(lotto)
            val matchBonus = lotto.contains(bonus)
            val rank = Rank.valueOf(countOfMatch, matchBonus)

            rankCountMap[rank] = rankCountMap.getOrDefault(rank, DEFAULT_COUNT) + COUNT_STEP
        }

        return LottoDrawingResult(rankCountMap)
    }

    companion object {
        private const val DEFAULT_COUNT = 0
        private const val COUNT_STEP = 1
    }
}
