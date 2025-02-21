package lotto.model

import lotto.Constants

class LottoTicket(
    private val numbers: List<LottoNumber> = generateLotto(),
    private val bonusNumber: LottoNumber? = null,
) {
    init {
        require(numbers.size == LOTTO_PICK_COUNT)
        require(numbers.size == numbers.toSet().size)
        require(!numbers.contains(bonusNumber))
    }

    fun getSize() = numbers.size

    fun getNumbers() = numbers

    fun getBonusNumber() = bonusNumber

    companion object {
        private fun generateLotto(): List<LottoNumber> =
            (Constants.MINIMUM_NUMBER..Constants.MAXIMUM_NUMBER)
                .shuffled()
                .take(LOTTO_PICK_COUNT)
                .sorted()
                .map { LottoNumber(it) }

        private const val LOTTO_PICK_COUNT = 6
    }
}
