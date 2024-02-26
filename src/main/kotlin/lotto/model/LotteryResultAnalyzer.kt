package lotto.model

class LotteryResultAnalyzer(private val winning: Lotto, private val bonusNumber: LottoNumber) {
    init {
        require(!winning.hasMatchingBonusNumbers(bonusNumber)) { "보너스 숫자는 당첨 번호와 중복될 수 없습니다." }
    }

    fun generateWinningStatus(lottoTickets: List<Lotto>): Map<WinningRank, Int> =
        lottoTickets.map {
            WinningRank.determineRank(
                it.countMatchingNumbers(winning),
                it.hasMatchingBonusNumbers(bonusNumber),
            )
        }.groupingBy { it }.eachCount()
}
