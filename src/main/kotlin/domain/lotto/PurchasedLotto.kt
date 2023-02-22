package domain.lotto

import domain.lotto.number.LottoNumber
import domain.rank.Rank
import java.util.TreeSet

open class PurchasedLotto(lottoNumbers: Set<LottoNumber>) : Lotto(lottoNumbers) {
    fun getSortedLotto(): Lotto = Lotto(TreeSet(this))

    fun matchLotto(winningLotto: WinningLotto, bonusNumber: LottoNumber): Rank {
        val (countOfMatch, matchBonus) = winningLotto.matchLotto(this, bonusNumber)
        return Rank.valueOf(countOfMatch, matchBonus)
    }
}
