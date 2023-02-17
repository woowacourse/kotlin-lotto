package domain

import domain.lotto.Lotto
import domain.lotto.LottoBundleDto

object Judgement {
    fun compareLottoBundle(winningNumbers: WinningNumbers, lottoBundleDto: LottoBundleDto): WinningResult {
        return WinningResult(lottoBundleDto.lottos.map { compareLotto(winningNumbers, it) })
    }

    private fun compareLotto(winningNumbers: WinningNumbers, lotto: Lotto): ComparingResultDto {
        return winningNumbers.compareLotto(lotto)
    }
}
