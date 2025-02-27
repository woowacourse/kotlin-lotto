package lotto.model

import lotto.contants.LottoRuleConstants

class LottoTicket(
    private val numbers: List<LottoNumber>,
) {
    private val lottoNumbers: Set<LottoNumber> = numbers.toSet()

    init {
        require(numbers.size == LottoRuleConstants.LOTTO_PICK_COUNT.value)
        { ERROR_NUMBERS_COUNT }
    }

    fun getSize() = lottoNumbers.size

    fun containsNumber(number: LottoNumber) : Boolean {
        return lottoNumbers.contains(number)
    }

    fun getNumbers() : Set<LottoNumber> {
        return lottoNumbers
    }

    companion object {
        private const val ERROR_NUMBERS_COUNT = "로또 번호의 개수는 6개입니다."

        fun create(vararg lottoNumbers: Int): LottoTicket {
            return LottoTicket(lottoNumbers.map { LottoNumber(it) })
        }
    }
}
