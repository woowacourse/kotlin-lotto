package lotto.domain.model

import lotto.Constants

class LottoTicket(
    numbers: List<LottoNumber>,
) {
    private val _numbers: List<LottoNumber> = numbers
    val numbers: Set<LottoNumber> get() = _numbers.toSet()

    fun countMatchingNumbers(winningNumbers: Set<LottoNumber>): Int = numbers.intersect(winningNumbers).size

    fun hasNumber(number: LottoNumber): Boolean = numbers.contains(number)

    companion object {
        private const val ERROR_LOTTO_INVALID_COUNT = "로또 번호는 6개여야 합니다."
        private const val ERROR_LOTTO_DUPLICATE = "로또 번호는 서로 중복되면 안 됩니다."

        fun create(numbers: List<LottoNumber>): Result<LottoTicket> =
            runCatching {
                require(numbers.size == Constants.LOTTO_PICK_COUNT) { ERROR_LOTTO_INVALID_COUNT }
                require(numbers.toSet().size == Constants.LOTTO_PICK_COUNT) { ERROR_LOTTO_DUPLICATE }
                LottoTicket(numbers.sortedBy { it.number })
            }
    }
}
