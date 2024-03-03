package lotto.model

class LottoWinningRank(
    private val winningNumbers: List<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    fun makeWinningTable(userTickets: List<UserLottoTicket>): WinningTable {
        val rankList = userTickets.map { it.getRank(winningNumbers, bonusNumber) }
        val winningMap = rankList.groupingBy { it }.eachCount().toMutableMap()
        val winningTable = WinningTable(winningMap)
        return winningTable
    }
}
