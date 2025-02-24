package lotto.model

import lotto.contants.LottoRuleConstants

class LottoTicket(
    private val numbers: Set<LottoNumber> = generateLotto(),
) {
    init {
        require(numbers.size == LottoRuleConstants.LOTTO_PICK_COUNT.value)
        { ERROR_NUMBERS_COUNT }
    }

    fun getSize() = numbers.size

    fun getNumbers() = numbers

    companion object {
        private const val ERROR_NUMBERS_COUNT = "로또 번호의 개수는 6개입니다."

        private fun generateLotto(): Set<LottoNumber> =
            (LottoRuleConstants.MINIMUM_NUMBER.value..LottoRuleConstants.MAXIMUM_NUMBER.value)
                .shuffled()
                .take(LottoRuleConstants.LOTTO_PICK_COUNT.value)
                .sorted()
                .map { LottoNumber(it) }
                .toSet()

            fun create(vararg lottoNumbers: LottoNumber): LottoTicket = LottoTicket(lottoNumbers.toSet())
    }
}
