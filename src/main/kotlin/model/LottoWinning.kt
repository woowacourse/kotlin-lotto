package model

class LottoWinning(
    private val winningTicket: LottoTicket,
    private val bonusNumber: Int,
    private val lottoTickets: List<LottoTicket>,
) {
    fun countMatchNumber(userTicket: LottoTicket): Int {
        return winningTicket.lottoTicket.intersect(userTicket.lottoTicket.toSet()).size
    }

    fun isBonusInTicket(userTicket: LottoTicket): Boolean {
        return userTicket.lottoTicket.contains(bonusNumber)
    }

    fun getRankList(): List<Rank> =
        lottoTickets.map {
            val countOfMatch = countMatchNumber(it)
            val hasBonusNumber = isBonusInTicket(it)
            Rank.decideRank(countOfMatch, hasBonusNumber)
        }

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
