package lotto.model

class LottoMatcher(
    private val winningLotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!winningLotto.getNumbers().contains(bonusNumber)) {
            BONUS_DUPLICATE_MESSAGE
        }
    }

    fun matchLotto(publishedLotto: List<Lotto>): Map<Rank, Int> {
        val rankMap: MutableMap<Rank, Int> = enumValues<Rank>().associateWith { 0 }.toMutableMap()
        publishedLotto
            .groupingBy { calculateRank(it) }
            .eachCount()
            .forEach { (rank, count) -> rankMap[rank] = count }

        return rankMap
    }

    private fun calculateRank(other: Lotto): Rank {
        val matchCount = winningLotto.countMatchedNumber(other)
        val matchBonus = other.findNumber(bonusNumber)
        return Rank.valueOf(matchCount, matchBonus)
    }

    companion object {
        private const val BONUS_DUPLICATE_MESSAGE = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다."
    }
}
