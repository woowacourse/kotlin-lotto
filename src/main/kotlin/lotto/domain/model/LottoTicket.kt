package lotto.domain.model

class LottoTicket(
    private val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == numbers.toSet().size) {
            ERROR_LOTTO_DUPLICATE
        }
//        require(!numbers.contains(bonusNumber)) {
//            ERROR_BONUS_DUPLICATE
//        }
    }

    fun getSize() = numbers.size

    fun getNumbers() = numbers

    fun countMatchingNumbers(winningNumbers: Set<LottoNumber>): Int = numbers.intersect(winningNumbers).size

    fun hasBonusNumber(bonusNumber: LottoNumber): Boolean = numbers.contains(bonusNumber)

    companion object {
        private const val ERROR_LOTTO_DUPLICATE = "로또 번호는 서로 중복되면 안 됩니다."
//        private const val ERROR_BONUS_DUPLICATE = "보너스 번호는 당첨 번호와 중복되면 안 됩니다."
    }
}
