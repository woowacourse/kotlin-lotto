package lotto.model

class WinningLotto(
    val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!lotto.contains(bonusNumber)) { "당첨 번호와 보너스 번호는 중복될 수 없습니다." }
    }

    fun isBonusMatch(lotto: Lotto): Boolean = lotto.contains(bonusNumber)

    fun match(number: Lotto): Rank {
        val matchCount = lotto.matchCount(number)
        val bonusMatch = number.contains(bonusNumber)
        return Rank.valueOf(matchCount, bonusMatch)
    }
}
