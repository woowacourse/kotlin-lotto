package lotto.domain

class WinningLotto(
    private val winningLotto: Lotto,
    private val bounusNumber: LottoNumber,
) {
    init {
        require(!winningLotto.lottoNums.contains(bounusNumber)) { BONUS_BALL_DUPLICATE_ERROR_MESSAGE }
        validateBonusNumber(bounusNumber)
    }

    fun getRank(lotto: Lotto): Rank {
        val matchCount = lotto.lottoNums.count { number -> winningLotto.compareWithNumber(number) }
        val matchBonus = lotto.compareWithNumber(this.bounusNumber)
        return Rank.valueOf(matchCount, matchBonus)
    }

    private fun validateBonusNumber(bonusNumber: LottoNumber) {
        require(!winningLotto.compareWithNumber(bonusNumber)) {
            BONUS_BALL_DUPLICATE_ERROR_MESSAGE
        }
    }

    companion object {
        private const val BONUS_BALL_DUPLICATE_ERROR_MESSAGE = "보너스 볼 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
