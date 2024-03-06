package lotto.model

import lotto.exception.Exceptions
import lotto.model.Lotto.Companion.LOTTO_SIZE

class WinningLotto(private val winningNumbers: List<LottoNumber>, private val bonusNumber: LottoNumber) {
    init {
        if (winningNumbers.size != LOTTO_SIZE) {
            throw Exceptions.WinningLottoSizeException()
        }
        if (winningNumbers.toSet().size != LOTTO_SIZE) {
            throw Exceptions.WinningLottoDuplication()
        }
        if (winningNumbers.contains(bonusNumber)) {
            throw Exceptions.BonusNumberDuplicationWithWinningNumber()
        }
    }

    fun judgeRank(lottoNumbers: Set<LottoNumber>): Rank {
        val countOfMatch = calculateCountOfMatch(lottoNumbers)
        val bonusMatched = checkBonusNumberMatched(lottoNumbers)

        return Rank.getRank(countOfMatch, bonusMatched)
    }

    private fun calculateCountOfMatch(lottoNumbers: Set<LottoNumber>): Int {
        return winningNumbers.count { winningNumber ->
            lottoNumbers.any { it.number == winningNumber.number }
        }
    }

    private fun checkBonusNumberMatched(lottoNumbers: Set<LottoNumber>): Boolean {
        return lottoNumbers.any { it.number == bonusNumber.number }
    }

    fun makeWinningStatics(lottos: Lottos): WinningStatistics {
        return lottos.publishedLottos
            .groupBy { judgeRank(it.numbers) }
            .mapValues { it.value.size }
            .let(::WinningStatistics)
    }
}
