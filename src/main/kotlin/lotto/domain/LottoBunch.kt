package lotto.domain

class LottoBunch(val value: List<Lotto>) {

    fun getRanks(winningLotto: WinningLotto): List<Rank> =
        value.map { getRank(it, winningLotto) }

    private fun getRank(lotto: Lotto, winningLotto: WinningLotto): Rank =
        Rank.convertToRank(
            countMatchedMainLottoNumber(lotto, winningLotto),
            checkMatchedBonusLottoNumber(lotto, winningLotto),
        )

    private fun countMatchedMainLottoNumber(lotto: Lotto, winningLotto: WinningLotto): Int =
        lotto.lottoNumbers.count { lottoNumber -> lottoNumber.value in winningLotto.mainLottoNumbers.lottoNumbers.map { it.value } }

    private fun checkMatchedBonusLottoNumber(lotto: Lotto, winningLotto: WinningLotto): Boolean =
        winningLotto.bonusLottoNumber.value in lotto.lottoNumbers.map { lottoNumber -> lottoNumber.value }

    fun sumTotalPrizeMoney(winningResult: WinningResult): Int =
        winningResult.value.keys.fold(0) { acc, rank -> acc + getPrizeMoney(rank, winningResult) }

    private fun getPrizeMoney(rank: Rank, winningResult: WinningResult): Int =
        rank.prizeMoney * (winningResult.value[rank] ?: throw IllegalStateException())
}
