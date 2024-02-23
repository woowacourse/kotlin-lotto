package lotto.model

import lotto.util.WinningRank

class LotteryResult(private val winning: Lotto, private val bonusNumber: LottoNumber) {
    init {
        require(!winning.compareBonusNumbers(bonusNumber)) { "보너스 숫자는 당첨 번호와 중복될 수 없습니다." }
    }

    fun generateWinningStatus(lottoTickets: List<Lotto>): Map<WinningRank, Int> =
        lottoTickets.map {
            WinningRank.convert(
                it.matchWinningNumbers(winning),
                it.compareBonusNumbers(bonusNumber),
            )
        }.groupingBy { it }.eachCount()
}
