package lotto.domain.model.winning

import lotto.domain.enums.Rank
import lotto.domain.model.Lotto
import lotto.domain.valueobject.LottoNumber

class WinningLotto(
    private val winningNumbers: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!winningNumbers.lottoNumbers.map { it.number }.contains(bonusNumber.number)) {
            HAS_DUPLICATE_BONUS_NUMBERS.format(
                bonusNumber.number,
                winningNumbers.lottoNumbers.map { it.number }.joinToString(","),
            )
        }
    }

    fun getRank(lotto: Lotto): Rank {
        val countOfMatch = getCountOfMatch(lotto)
        val matchBonus = isBonusMatched(lotto)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun getCountOfMatch(lotto: Lotto): Int {
        val winningNumbers = winningNumbers.lottoNumbers.map { it.number }
        val lottoNumbers = lotto.lottoNumbers.map { it.number }
        return winningNumbers.intersect(lottoNumbers.toSet()).size
    }

    private fun isBonusMatched(lotto: Lotto): Boolean = lotto.lottoNumbers.map { it.number }.contains(bonusNumber.number)

    companion object {
        private const val HAS_DUPLICATE_BONUS_NUMBERS = "보너스 번호 %d이 당첨 번호 %s에 이미 존재합니다."
    }
}
