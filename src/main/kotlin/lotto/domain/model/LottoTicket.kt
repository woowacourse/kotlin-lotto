package lotto.domain.model

class LottoTicket(
    private val numbers: Set<LottoNumber>,
) {
    fun getNumbers() = numbers

    fun countMatchingNumbers(winningNumbers: Set<LottoNumber>): Int = numbers.intersect(winningNumbers).size

    fun hasBonusNumber(bonusNumber: LottoNumber): Boolean = numbers.contains(bonusNumber)
}
