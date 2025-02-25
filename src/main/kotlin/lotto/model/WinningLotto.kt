package lotto.model

class WinningLotto(
    val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!lotto.getNumbers().contains(bonusNumber)) { "당첨 번호와 보너스 번호는 중복될 수 없습니다." }
    }

    fun match(lotto: Lotto): MatchResult {
        val matchCount = countMatchingNumbers(lotto.getNumbers())
        val isBonusMatch = containsNumber(bonusNumber)
        return MatchResult(matchCount, isBonusMatch)
    }

    fun countMatchingNumbers(numbers: List<LottoNumber>): Int = numbers.count { this.lotto.getNumbers().contains(it) }

    fun containsNumber(number: LottoNumber): Boolean = lotto.getNumbers().contains(number)

    data class MatchResult(val matchCount: Int, val isBonusMatch: Boolean)
}
