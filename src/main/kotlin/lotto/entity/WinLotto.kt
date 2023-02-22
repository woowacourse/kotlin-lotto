package lotto.entity

import lotto.model.Rank

class WinLotto(private val winNumber: Lotto, private val bonus: LottoNumber) {
    init {
        require(!winNumber.numbers.contains(bonus)) {
            ERROR_MESSAGE_DUPLICATED_BONUS_NUMBER.format(
                winNumber.toString(),
                bonus.value
            )
        }
    }

    fun makeWinStatistics(purchasedLottos: PurchasedLottos): WinStatistics =
        WinStatistics(purchasedLottos.value.map { determineRank(it) })

    private fun determineRank(lotto: Lotto): Rank {
        val countOfMatch = lotto.matchLottoNumberCount(winNumber)
        val isMatchBonus = lotto.isMatchBonus(bonus)
        val determinedRank = Rank.values().find { it.determine(countOfMatch, isMatchBonus) }
        check(determinedRank != null) {
            String.format(ERROR_DETERMINED_RANK_IS_EMPTY)
        }
        return determinedRank
    }

    companion object {
        const val ERROR_MESSAGE_DUPLICATED_BONUS_NUMBER = "보너스 번호와 당첨 번호는 중복될 수 없습니다. 입력된 당첨 번호 : %s, 보너스 번호 : %d"
        private const val ERROR_DETERMINED_RANK_IS_EMPTY = "등수 판별에 실패하였습니다."
    }
}
