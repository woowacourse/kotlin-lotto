package domain.model

import domain.model.LottoNumber.Companion.INVALID_LOTTO_NUMBERS
import domain.service.LottoGenerator.Companion.LOTTO_MAX
import domain.service.LottoGenerator.Companion.LOTTO_MIN
import util.ErrorConstants.ERROR

class WinningLotto(
    val winningNumbers: Lotto,
    val bonusNumber: Int,
) {
    init {
        require(bonusNumber !in winningNumbers.numbers.map { it.value }) {
            DUPLICATED_BONUS_NUMBER
        }

        require(bonusNumber in LOTTO_MIN..LOTTO_MAX) { INVALID_LOTTO_NUMBERS }
    }
    private fun getRank(buyLotto: List<LottoNumber>): Rank {
        val winningLottoNumbers = winningNumbers.numbers

        val lottoMatches = buyLotto.intersect(winningLottoNumbers).size
        val isBonusMatched = bonusNumber in buyLotto.map { it.value }

        val rank = Rank.valueOf(lottoMatches, isBonusMatched)
        return rank
    }

    companion object {
        const val DUPLICATED_BONUS_NUMBER = "$ERROR 보너스 번호와 로또 번호는 중복될 수 없습니다."
    }
}
