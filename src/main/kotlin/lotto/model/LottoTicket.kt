package lotto.model

import lotto.contants.Constants

class LottoTicket(
    private val numbers: Set<LottoNumber> = generateLotto(),
) {
    init {
        require(numbers.size == Constants.LOTTO_PICK_COUNT)
    }

    fun getSize() = numbers.size

    fun getNumbers() = numbers

    companion object {
        private fun generateLotto(): Set<LottoNumber> =
            (Constants.MINIMUM_NUMBER..Constants.MAXIMUM_NUMBER)
                .shuffled()
                .take(Constants.LOTTO_PICK_COUNT)
                .sorted()
                .map { LottoNumber(it) }
                .toSet()
    }
}
