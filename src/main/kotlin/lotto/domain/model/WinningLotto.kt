package lotto.domain.model

import lotto.domain.value.LottoNumber
import lotto.enums.Rank

class WinningLotto(
    private val winningNumbers: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!winningNumbers.contains(bonusNumber)) { ERROR_DUPLICATE_LOTTO_NUMBER }
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

    companion object {
        private const val ERROR_DUPLICATE_LOTTO_NUMBER = "[ERROR] 당첨 번호와 보너스 번호는 중복되면 안됩니다."
    }
}
