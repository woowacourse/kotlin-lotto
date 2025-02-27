package domain.model.lotto

import domain.model.Rank
import domain.model.number.LottoNumber

data class Lotto(val numbers: List<LottoNumber>) {
    init {
        if (numbers.size != LOTTO_SIZE) {
            throw LottoException.InvalidLottoSize()
        }
        if (numbers.size != numbers.toSet().size) {
            throw LottoException.DuplicatedLottoSize()
        }
    }

    fun getRank(
        winningLotto: Lotto,
        bonusNumber: LottoNumber
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
