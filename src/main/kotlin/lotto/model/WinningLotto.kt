package lotto.model

class WinningLotto(
    val winningNumbers: Lotto,
    val bonusNumber: LottoNumber,
) {
    init {
        require(!winningNumbers.contains(bonusNumber)) { ERROR_DUPLICATED_BONUS_NUMBER }
    }

    fun findLottoRank(lotto: Lotto): Rank {
        val matchCount =
            winningNumbers.numbers.count { winningLottoNumber ->
                lotto.contains(winningLottoNumber)
            }
        val isMatchedBonus = lotto.contains(bonusNumber)
        return Rank.valueOf(matchCount, isMatchedBonus)
    }

    companion object {
        private const val ERROR_DUPLICATED_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
