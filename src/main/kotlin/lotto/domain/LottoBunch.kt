package lotto.domain

class LottoBunch(val value: List<Lotto>) {

    fun calcRanks(winningLotto: WinningLotto): List<Rank> =
        value.map { getRank(it, winningLotto) }

    private fun getRank(lotto: Lotto, winningLotto: WinningLotto): Rank =
        Rank.convertToRank(
            winningLotto.countMatchedMainLottoNumber(lotto),
            winningLotto.checkMatchedBonusLottoNumber(lotto),
        )
}
