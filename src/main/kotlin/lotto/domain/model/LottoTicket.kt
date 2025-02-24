package lotto.domain.model

class LottoTicket(
    private val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == LOTTO_PICK_COUNT) { ERROR_INVALID_WINNING_COUNT }
    }

    fun getNumbers() = numbers

    fun countMatchingNumbers(winningNumbers: Set<LottoNumber>): Int = numbers.intersect(winningNumbers).size

    fun hasBonusNumber(bonusNumber: LottoNumber): Boolean = numbers.contains(bonusNumber)

    companion object {
        private const val ERROR_INVALID_WINNING_COUNT = "당첨 번호는 6개여야 합니다."
        private const val LOTTO_PICK_COUNT = 6
    }
}
