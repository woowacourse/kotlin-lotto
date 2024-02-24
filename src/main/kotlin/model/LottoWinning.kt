package model

class LottoWinning(
    private val winningTicket: LottoTicket,
    private val bonusNumber: LottoNumber,
    private val lottoTickets: List<LottoTicket>,
) {
    fun getRankList(): List<Rank> =
        lottoTickets.map {
            val countOfMatch = countMatchNumber(it)
            val hasBonusNumber = bonusNumber in it
            Rank.decideRank(countOfMatch, hasBonusNumber)
        }

    private fun countMatchNumber(userTicket: LottoTicket): Int = (userTicket intersect winningTicket).size

    fun makeWinningChart(): LottoResult {
        val rankList = getRankList()
        return LottoResult(rankList.groupingBy { it }.eachCount())
    }

    private fun calculateWinningPrize(): Int {
        val rankList = getRankList()
        val winningPrize =
            rankList.sumOf {
                it.winningMoney
            }
        return winningPrize
    }

    fun calculateWinningRate(): Float {
        val winningPrize = calculateWinningPrize()
        val purchaseAmount = lottoTickets.size * LottoPurchase.PRICE_OF_LOTTO_TICKET
        return winningPrize.toFloat() / purchaseAmount
    }
}
