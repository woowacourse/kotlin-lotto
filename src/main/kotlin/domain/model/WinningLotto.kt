package domain.model

class WinningLotto(
    val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(bonusNumber.value !in lotto.numbers) { DUPLICATED_BONUS_NUMBER }
    }

    fun match(lotto: Lotto): Rank {
        val lottoNumbers = lotto.numbers
        val matchCount = lottoNumbers.intersect(lotto.numbers.toSet()).size
        val isBonusMatched = bonusNumber.value in lottoNumbers

        return Rank.valueOf(matchCount, isBonusMatched)
    }

    companion object {
        const val ERROR = "[ERROR]"
        const val DUPLICATED_BONUS_NUMBER = "$ERROR 보너스 번호는 로또 번호는 중복될 수 없습니다."
    }
}
