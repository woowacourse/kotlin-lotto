package model

class LottoWinning(
    private val winningTicket: LottoTicket,
    private val bonusNumber: LottoNumber,
    private val lottoTickets: List<LottoTicket>,
) {
    private fun getRankList(): List<Rank> =
        lottoTickets.map {
            val countOfMatch = countMatchNumber(it)
            val hasBonusNumber = bonusNumber in it
            Rank.decideRank(countOfMatch, hasBonusNumber)
        }

    private fun countMatchNumber(userTicket: LottoTicket): Int = (userTicket intersect winningTicket).size

    fun makeLottoResult(): LottoResult {
        val rankList = getRankList()
        return LottoResult(rankList.groupingBy { it }.eachCount())
    }

    fun calculateWinningRate(): Float {
        val winningPrize = makeLottoResult().winningMoney
        val purchaseAmount = lottoTickets.size * LottoPurchase.PRICE_OF_LOTTO_TICKET
        return winningPrize.toFloat() / purchaseAmount
    }
}
