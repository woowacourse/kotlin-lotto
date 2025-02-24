package lotto.model

import lotto.contants.LottoRuleConstants

class LottoTicket(
    private val numbers: Set<LottoNumber> = generateLotto(),
) {
    init {
        require(numbers.size == LottoRuleConstants.LOTTO_PICK_COUNT.value)
    }

    fun getSize() = numbers.size

    fun getNumbers() = numbers

    companion object {
        private fun generateLotto(): Set<LottoNumber> =
            (LottoRuleConstants.MINIMUM_NUMBER.value..LottoRuleConstants.MAXIMUM_NUMBER.value)
                .shuffled()
                .take(LottoRuleConstants.LOTTO_PICK_COUNT.value)
                .sorted()
                .map { LottoNumber(it) }
                .toSet()
    }
}
