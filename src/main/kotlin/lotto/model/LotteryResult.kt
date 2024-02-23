package lotto.model

import lotto.util.WinningRank

class LotteryResult(private val winning: Lotto, private val bonusNumber: LottoNumber, lottoTickets: List<Lotto>) {
    private val winningResult: List<WinningRank>

    init {
        require(!winning.compareBonusNumbers(bonusNumber)) { "보너스 숫자는 당첨 번호와 중복될 수 없습니다." }
        winningResult =
            lottoTickets.map {
                WinningRank.convert(
                    it.matchWinningNumbers(winning),
                    it.compareBonusNumbers(bonusNumber),
                )
            }
    }

    fun generateWinningStatus(): Map<WinningRank, Int> = winningResult.groupingBy { it }.eachCount()
}
