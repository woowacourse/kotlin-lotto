package lotto.domain

class LottoTickets(val lottoTickets: List<LottoTicket>) {
    fun countOfRank(winningLotto: WinningLotto): Map<Rank, Int> {
        return lottoTickets.map { it.countOfSameNumbers(winningLotto.winningLottoTicket) }
            .map { Rank.of(it) }
            .groupingBy { it }
            .eachCount()
    }
}
