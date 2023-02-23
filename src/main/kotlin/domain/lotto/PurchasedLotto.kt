package domain.lotto

import domain.lotto.number.LottoNumber
import domain.rank.Rank
import java.util.*

open class PurchasedLotto(lottoNumbers: Set<LottoNumber>) : Lotto(lottoNumbers) {
    fun getSortedLotto(): Lotto = PurchasedLotto(TreeSet(this))

    fun matchLotto(winningLotto: WinningLotto, bonusNumber: LottoNumber): Rank {
        val (countOfMatch, matchBonus) = winningLotto.matchLotto(this, bonusNumber)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
