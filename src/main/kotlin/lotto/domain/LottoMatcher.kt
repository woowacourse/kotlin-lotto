package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.Rank

class LottoMatcher(
    private val winningLotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!winningLotto.isContain(bonusNumber)) {
            BONUS_DUPLICATE_MESSAGE
        }
    }

    fun matchLotto(publishedLotto: List<Lotto>): Map<Rank, Int> {
        return publishedLotto.groupingBy { calculateRank(it) }
            .eachCount()
            .let { matchingResult ->
                enumValues<Rank>().associateWith { matchingResult[it] ?: 0 }
            }
    }

    private fun calculateRank(lotto: Lotto): Rank {
        val matchCount = lotto.countMatchingNumber(winningLotto)
        val matchBonus = lotto.isContain(bonusNumber)
        return Rank.valueOf(matchCount, matchBonus)
    }

    companion object {
        private const val BONUS_DUPLICATE_MESSAGE = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다."
    }
}
