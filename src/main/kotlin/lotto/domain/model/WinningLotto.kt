package lotto.domain.model

import lotto.constants.ErrorMessages
import lotto.domain.value.LottoNumber
import lotto.enums.Rank

class WinningLotto(
    private val winningNumbers: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(
            !winningNumbers.lottoNumbers
                .map { it.number }
                .contains(bonusNumber.number),
        ) { ErrorMessages.DUPLICATE_LOTTO_NUMBER }
    }

    fun getRank(lotto: Lotto): Rank {
        val countOfMatch = getCountOfMatch(lotto)
        val matchBonus = getMatchBonus(lotto)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun getCountOfMatch(lotto: Lotto): Int =
        winningNumbers.lottoNumbers.count {
            lotto.lottoNumbers.contains(it)
        }

    private fun getMatchBonus(lotto: Lotto): Boolean = lotto.lottoNumbers.map { it.number }.contains(bonusNumber.number)
}
