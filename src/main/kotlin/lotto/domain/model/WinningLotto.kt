package lotto.domain.model

import lotto.constants.ErrorMessages
import lotto.domain.value.LottoNumber
import lotto.enums.Rank

class WinningLotto(
    private val winningNumbers: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!winningNumbers.contains(bonusNumber)) { ErrorMessages.DUPLICATE_LOTTO_NUMBER }
    }

    fun getLottoResult(lottos: List<Lotto>): LottoResult {
        val winningStats = getWinningStats(lottos)
        return LottoResult(winningStats)
    }

    private fun getWinningStats(lottos: List<Lotto>): Map<Rank, Int> = lottos.groupingBy { getRank(it) }.eachCount()

    private fun getRank(lotto: Lotto): Rank {
        val countOfMatch = lotto.count(winningNumbers)
        val matchBonus = lotto.contains(bonusNumber)
        return Rank.valueOf(countOfMatch, matchBonus)
    }
}
