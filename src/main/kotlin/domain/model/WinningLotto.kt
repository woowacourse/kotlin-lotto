package domain.model

import util.ErrorConstants.ERROR

class WinningLotto(
    val lotto: Lotto,
    val bonusNumber: BonusNumber,
    val winningNumbers: Lotto,
    val bonusNumber: Int,
) {
    init {
        require(bonusNumber !in winningNumbers.numbers.map { it.value }) {
            DUPLICATED_BONUS_NUMBER
        }

        require(bonusNumber in LOTTO_MIN..LOTTO_MAX) { INVALID_BONUS_NUMBER }
    }
    private fun getRank(buyLotto: List<LottoNumber>): Rank {
        val winningLottoNumbers = winningNumbers.numbers

        val lottoMatches = buyLotto.intersect(winningLottoNumbers).size
        val isBonusMatched = bonusNumber in buyLotto.map { it.value }

        val rank = Rank.valueOf(lottoMatches, isBonusMatched)
        return rank
    }

    companion object {
        const val DUPLICATED_BONUS_NUMBER = "$ERROR 보너스 번호는 로또 번호는 중복될 수 없습니다."
        const val INVALID_BONUS_NUMBER = "$ERROR 보너스 볼 번호는 ${LOTTO_MIN}부터 $LOTTO_MAX 사이입니다."
    }
}
