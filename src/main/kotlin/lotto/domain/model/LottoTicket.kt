package lotto.domain.model

import lotto.Constants

class LottoTicket(
    numbers: List<LottoNumber>,
) {
    private val numbers: Set<LottoNumber>

    init {
        require(numbers.size == Constants.LOTTO_PICK_COUNT) { ERROR_LOTTO_INVALID_COUNT }
        require(numbers.toSet().size == Constants.LOTTO_PICK_COUNT) { ERROR_LOTTO_DUPLICATE }
        this.numbers = numbers.toSet()
    }

    fun toSet(): Set<LottoNumber> = numbers

    fun countMatchingNumbers(winningNumbers: Set<LottoNumber>): Int = numbers.intersect(winningNumbers).size

    fun hasNumber(number: LottoNumber): Boolean = numbers.contains(number)

    companion object {
        private const val ERROR_LOTTO_INVALID_COUNT = "로또 번호는 6개여야 합니다."
        private const val ERROR_LOTTO_DUPLICATE = "로또 번호는 서로 중복되면 안 됩니다."
    }
}
