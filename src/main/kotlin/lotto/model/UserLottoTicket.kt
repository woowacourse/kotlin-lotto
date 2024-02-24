package lotto.model

class UserLottoTicket(val userLottoTicket: List<LottoNumber>) {
    init {
        require(userLottoTicket.size == 6)
    }

    private fun countMatchNumber(winningNumbers: List<LottoNumber>): Int {
        return winningNumbers.intersect(userLottoTicket.toSet()).size
    }

    private fun isBonusInTicket(bonusNumber: LottoNumber): Boolean {
        return userLottoTicket.contains(bonusNumber)
    }

    fun getRank(
        winningNumbers: List<LottoNumber>,
        bonusNumber: LottoNumber,
    ): Rank {
        val countOfMatch = countMatchNumber(winningNumbers)
        val hasBonusNumber = isBonusInTicket(bonusNumber)
        return Rank.decideRank(countOfMatch, hasBonusNumber)
    }
}
