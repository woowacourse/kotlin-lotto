package lotto.model

class WinningLotto(private val winning: Lotto, private val bonusNumber: LottoNumber) {
    init {
        require(bonusNumber !in winning) { "보너스 숫자는 당첨 번호와 중복될 수 없습니다." }
    }

    fun generateWinningStatus(lottoTickets: List<Lotto>): WinningStatus =
        WinningStatus(lottoTickets.map { determineWinningRank(it) }.groupingBy { it }.eachCount())

    private fun determineWinningRank(lotto: Lotto): WinningRank =
        WinningRank.determineRank(
            lotto.countMatchingNumbers(winning),
            bonusNumber in lotto,
        )
}
