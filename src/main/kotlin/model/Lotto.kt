package model

class Lotto(
    private val purchasePrice: Int,
    private val winningTicket: LottoTicket,
    private val bonusNumber: Int,
) {
    fun getUserTickets(): List<LottoTicket> {
        val userTickets: MutableList<LottoTicket> = mutableListOf()
        repeat(getLottoCount()) {
            userTickets.add(makeUserTicket())
        }
        return userTickets
    }

    fun makeUserTicket() = LottoTicket((1..45).shuffled().take(6))

    fun getLottoCount(): Int = purchasePrice / 1000

    fun countMatchNumber(userTicket: LottoTicket): Int {
        return winningTicket.lottoTicket.intersect(userTicket.lottoTicket.toSet()).size
    }

    fun getRankList(): List<Rank> = getUserTickets().map {
        val num = countMatchNumber(it)
        val hasBonusNumber = bonusIsInTicket(it)
        Rank.decideRank(num, hasBonusNumber)
    }

    fun calculateWinningPrize(): Int {
        val rankList = getRankList()
        val winningPrize = rankList.sumOf{
            it.winningMoney
        }
        return winningPrize
    }

    fun calculateWinningRate(): Float {
        val winningPrize = calculateWinningPrize()
        return winningPrize.toFloat()/purchasePrice
    }

    fun bonusIsInTicket(userTicket: LottoTicket): Boolean {
        return userTicket.lottoTicket.contains(bonusNumber)
    }

}
