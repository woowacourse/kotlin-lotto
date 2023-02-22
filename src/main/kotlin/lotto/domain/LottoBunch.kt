package lotto.domain

class LottoBunch(val value: List<Lotto>) {

    fun calcRanks(winningLotto: WinningLotto): List<Rank> =
        value.map { getRank(it, winningLotto) }

    private fun getRank(lotto: Lotto, winningLotto: WinningLotto): Rank =
        Rank.convertToRank(
            lotto.countMatchedMainLottoNumber(winningLotto),
            lotto.checkMatchedBonusLottoNumber(winningLotto),
        )

    fun sumTotalPrizeMoney(winningResult: WinningResult): Int =
        winningResult.rankTable.keys.fold(0) { acc, rank -> acc + getPrizeMoney(rank, winningResult) }

    private fun getPrizeMoney(rank: Rank, winningResult: WinningResult): Int =
        rank.prizeMoney * (winningResult.rankTable[rank] ?: throw IllegalStateException())
}
