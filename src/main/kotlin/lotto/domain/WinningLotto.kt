package lotto.domain

class WinningLotto(
    private val winningLotto: Lotto,
    private val bounusNumber: LottoNumber,
) {
    init {
        validateNumber(bounusNumber)
    }

    fun getRank(lotto: Lotto): Rank {
        val matchCount = lotto.lottoNums.count { number -> winningLotto.compareWithNumber(number) }
        val matchBonus = lotto.compareWithNumber(this.bounusNumber)
        return Rank.valueOf(matchCount, matchBonus)
    }

    private fun validateNumber(number: LottoNumber) {
        require(!winningLotto.compareWithNumber(number)) {
            BONUS_BALL_DUPLICATE_ERROR_MESSAGE
        }
    }

    companion object {
        private const val BONUS_BALL_DUPLICATE_ERROR_MESSAGE = "보너스 볼 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
