package model

class Lotto(
    private val purchasePrice: Int,
    private val winningTicket: LottoTicket,
    private val bonusNumber: Int,
) {
    fun makeLottoCount(): Int {
        return purchasePrice / 1000
    }

    fun makeUserTicket(): LottoTicket {
        return LottoTicket((1..45).shuffled().take(6))
    }

    fun makeUserTickets(): List<LottoTicket> {
        val userTickets: MutableList<LottoTicket> = mutableListOf()
        repeat(makeLottoCount()) {
            userTickets.add(makeUserTicket())
        }
        return userTickets
    }

    fun countMatchNumber(userTicket: LottoTicket): Int {
        return winningTicket.lottoTicket.intersect(userTicket.lottoTicket.toSet()).size
    }

    fun getRankList(): List<Rank> = makeUserTickets().map {
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

    fun makeWinningChart():Map<Rank, Int> {
        val rankList = getRankList()
        return rankList.groupingBy { it }.eachCount()
    }
}
