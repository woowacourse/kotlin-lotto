package model

class LottoWinning(
    private val winningTicket: LottoTicket,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(bonusNumber !in winningTicket)
    }
    private fun getRankList(lottoTickets: List<LottoTicket>): List<Rank> =
        lottoTickets.map {
            val countOfMatch = countMatchNumber(it)
            val hasBonusNumber = bonusNumber in it
            Rank.decideRank(countOfMatch, hasBonusNumber)
        }

    private fun countMatchNumber(userTicket: LottoTicket): Int = (userTicket intersect winningTicket).size

    fun makeLottoResult(lottoTickets: List<LottoTicket>): LottoResult {
        val rankList = getRankList(lottoTickets)
        return LottoResult(rankList.groupingBy { it }.eachCount())
    }
}
