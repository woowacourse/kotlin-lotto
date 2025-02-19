package lotto.model

class LottoMatcher(
    private val winningLotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!winningLotto.checkBonusNumber(bonusNumber)) {
            BONUS_DUPLICATE_MESSAGE
        }
    }

    fun calculateRank(publishedLotto: Lotto): Rank {
        val matchCount = publishedLotto.countMatchingNumber(winningLotto)
        val matchBonus = publishedLotto.checkBonusNumber(bonusNumber)
        return Rank.valueOf(matchCount, matchBonus)
    }

    companion object {
        private const val BONUS_DUPLICATE_MESSAGE = "[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다."
    }
}
