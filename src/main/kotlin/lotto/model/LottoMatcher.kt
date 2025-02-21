package lotto.model

class LottoMatcher(
    private val winningLotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    private val rankMap: MutableMap<Rank, Int> = enumValues<Rank>().associateWith { 0 }.toMutableMap()

    init {
        require(!checkBonusNumber(winningLotto, bonusNumber)) {
            BONUS_DUPLICATE_MESSAGE
        }
    }

    fun matchLotto(publishedLotto: List<Lotto>): Map<Rank, Int> {
        publishedLotto
            .groupingBy { calculateRank(it) }
            .eachCount()
            .forEach { (rank, count) -> rankMap[rank] = count }

        return rankMap
    }

    private fun calculateRank(lotto: Lotto): Rank {
        val matchCount = countMatchingNumber(lotto)
        val matchBonus = checkBonusNumber(lotto, bonusNumber)
        return Rank.valueOf(matchCount, matchBonus)
    }

    private fun countMatchingNumber(lotto: Lotto): Int =
        lotto
            .getNumbers()
            .intersect(winningLotto.getNumbers())
            .size

    private fun checkBonusNumber(
        publishedLotto: Lotto,
        bonusNumber: LottoNumber,
    ): Boolean = publishedLotto.getNumbers().contains(bonusNumber)

    companion object {
        private const val BONUS_DUPLICATE_MESSAGE = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다."
    }
}
