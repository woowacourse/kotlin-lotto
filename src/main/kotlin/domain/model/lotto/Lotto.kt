package domain.model.lotto

import domain.model.Rank
import domain.model.number.LottoNumber

data class Lotto(val numbers: Set<LottoNumber>) {
    constructor(numbers: List<LottoNumber>) : this(numbers.toSet()) {
        if (numbers.size != this.numbers.size) {
            throw LottoException.DuplicatedLottoSize()
        }
        if (numbers.size != LOTTO_SIZE) {
            throw LottoException.InvalidLottoSize()
        }
    }

    fun getRank(
        winningLotto: Lotto,
        bonusNumber: LottoNumber,
    ): Rank {
        val winningLottoNumbers = winningLotto.numbers

        val lottoMatches = numbers.intersect(winningLottoNumbers).size
        val isBonusMatched = numbers.contains(bonusNumber)

        val rank = Rank.Companion.valueOf(lottoMatches, isBonusMatched)
        return rank
    }

    companion object {
        const val LOTTO_SIZE = 6
    }
}
