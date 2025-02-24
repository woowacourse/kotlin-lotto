package lotto.model

import lotto.contants.LottoRuleConstants

class LottoTicket(
    private val numbers: Set<LottoNumber> = generateLotto(),
) {
    init {
        require(numbers.size == LottoRuleConstants.LOTTO_PICK_COUNT)
    }

    fun getSize() = numbers.size

    fun getNumbers() = numbers

    companion object {
        private fun generateLotto(): Set<LottoNumber> =
            (LottoRuleConstants.MINIMUM_NUMBER..LottoRuleConstants.MAXIMUM_NUMBER)
                .shuffled()
                .take(LottoRuleConstants.LOTTO_PICK_COUNT)
                .sorted()
                .map { LottoNumber(it) }
                .toSet()
    }
}
