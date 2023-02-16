package domain.lotto

class PurchasedLotto(lottoNumbers: List<LottoNumber>) : Lotto(lottoNumbers) {
    fun getSortedLotto(): Lotto = Lotto(this.sortedBy { it.value })

    fun matchLotto(winningLotto: WinningLotto, bonusNumber: BonusNumber): Rank {
        val (countOfMatch, matchBonus) = winningLotto.matchLotto(this, bonusNumber)
        return Rank.valueOf(countOfMatch, matchBonus)
    }
}
