package domain.model

import domain.model.Lotto.Companion.toValues

class WinningLotto(
    val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(bonusNumber.value !in lotto.lottoNumbers.map { it.value }) { DUPLICATED_BONUS_NUMBER }
    }

    fun match(lotto: Lotto): Rank {
        val lottoNumbers = lotto.lottoNumbers
        val matchCount = lottoNumbers.intersect(lotto.lottoNumbers.toSet()).size
        val isBonusMatched = bonusNumber.value in lottoNumbers.toValues()

        return Rank.valueOf(matchCount, isBonusMatched)
    }

    companion object {
        const val ERROR = "[ERROR]"
        const val DUPLICATED_BONUS_NUMBER = "$ERROR 보너스 번호는 로또 번호는 중복될 수 없습니다."
    }
}
