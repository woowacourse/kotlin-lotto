package domain.model

class WinningLotto(
    val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(bonusNumber !in lotto.numbers.map { it }) { DUPLICATED_BONUS_NUMBER }
    }

    fun match(purchaseLotto: Lotto): Rank {
        val purchaseLottoNumbers = purchaseLotto.numbers
        val matchCount = lotto.numbers.intersect(purchaseLotto.numbers.toSet()).size
        val isBonusMatched = bonusNumber in purchaseLottoNumbers

        return Rank.valueOf(matchCount, isBonusMatched)
    }

    companion object {
        const val ERROR = "[ERROR]"
        const val DUPLICATED_BONUS_NUMBER = "$ERROR 보너스 번호는 로또 번호는 중복될 수 없습니다."
    }
}
