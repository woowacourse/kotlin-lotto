package lotto.model

class LottoWinning(
    private val winningNumbers: List<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    fun makeRankMap(userTickets: List<UserLottoTicket>): Map<Rank, Int> {
        val rankList = userTickets.map { it.getRank(winningNumbers, bonusNumber) }
        val rankMap = rankList.groupingBy { it }.eachCount().toMutableMap()
        // Map에 각각의 Rank가 없을 경우(null일 경우) 0으로 바꿔준다.
        for (rank in Rank.entries) {
            rankMap[rank] = rankMap[rank] ?: 0
        }
        return rankMap
    }

    private fun calculateWinningPrize(userTickets: List<UserLottoTicket>): Int {
        val rankMap = makeRankMap(userTickets)
        val winningPrize = rankMap.map { it.key.winningMoney * it.value }.sum()
        return winningPrize
    }

    fun calculateWinningRate(userTickets: List<UserLottoTicket>): Float {
        val winningPrize = calculateWinningPrize(userTickets)
        val purchaseAmount = userTickets.size * LottoPurchase.PRICE_OF_LOTTO_TICKET
        return winningPrize.toFloat() / purchaseAmount
    }
}
