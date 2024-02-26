package lotto.model

class LottoWinning(
    private val userTickets: List<UserLottoTicket>,
    private val winningNumbers: List<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    fun makeRankMap(): Map<Rank?, Int> {
        val rankList = userTickets.map { it.getRank(winningNumbers, bonusNumber) }
        val rankMap = rankList.groupingBy { it }.eachCount().toMutableMap()
        // Map에 각각의 Rank가 없을 경우(null일 경우) 0으로 바꿔준다.
        for (rank in Rank.entries) {
            rankMap[rank] = rankMap[rank] ?: 0
        }
        return rankMap
    }

    private fun calculateWinningPrize(): Int {
        val rankMap = makeRankMap()
        val winningPrize = rankMap.map { it.key!!.winningMoney * it.value }.sum()
        return winningPrize
    }

    fun calculateWinningRate(): Float {
        val winningPrize = calculateWinningPrize()
        val purchaseAmount = userTickets.size * LottoPurchase.PRICE_OF_LOTTO_TICKET
        return winningPrize.toFloat() / purchaseAmount
    }
}
