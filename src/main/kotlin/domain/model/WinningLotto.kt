package domain.model

import util.ErrorConstants.ERROR

class WinningLotto(
    val winningNumbers: Lotto,
    val bonusNumber: LottoNumber,
) {
    init {
        require(bonusNumber.value !in winningNumbers.numbers.map { it.value }) {
            DUPLICATED_BONUS_NUMBER
        }
    }

    fun calculate(purchaseLotto: List<Lotto>): LottoMatchResult {
        val rankResult = Rank.rankMap().toMutableMap()
        purchaseLotto.forEach { lotto ->
            val rank = getRank(lotto.numbers)
            rankResult[rank] = rankResult.getOrDefault(rank, 0) + 1
        }
        return LottoMatchResult(rankResult)
    }

    private fun getRank(buyLotto: List<LottoNumber>): Rank {
        val winningLottoNumbers = winningNumbers.numbers

        val lottoMatches = buyLotto.intersect(winningLottoNumbers).size
        val isBonusMatched = bonusNumber.value in buyLotto.map { it.value }

        val rank = Rank.valueOf(lottoMatches, isBonusMatched)
        return rank
    }

    companion object {
        const val DUPLICATED_BONUS_NUMBER = "$ERROR 보너스 번호와 로또 번호는 중복될 수 없습니다."
    }
}
