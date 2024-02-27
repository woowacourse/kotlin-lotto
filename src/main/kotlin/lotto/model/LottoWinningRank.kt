package lotto.model

class LottoWinningRank(
    private val winningNumbers: List<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    fun makeRankMap(userTickets: List<UserLottoTicket>): Map<Rank, Int> {
        val rankList = userTickets.map { it.getRank(winningNumbers, bonusNumber) }
        val rankMap = rankList.groupingBy { it }.eachCount().toMutableMap()
        for (rank in Rank.entries) {
            rankMap[rank] = rankMap[rank] ?: 0
        }
        return rankMap
    }
}
