package domain.lotto

import domain.lotto.number.LottoNumber
import domain.rank.Rank

open class PurchasedLotto(lottoNumbers: List<LottoNumber>) : Lotto(lottoNumbers) {
    fun getSortedLotto(): Lotto = Lotto(this.sortedBy { it.value })

    fun matchLotto(winningLotto: WinningLotto, bonusNumber: LottoNumber): Rank {
        val (countOfMatch, matchBonus) = winningLotto.matchLotto(this, bonusNumber)
        return Rank.valueOf(countOfMatch, matchBonus)
    }
}
