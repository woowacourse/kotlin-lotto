package lotto.domain.model

import lotto.constants.ErrorMessages
import lotto.constants.LottoConstants
import lotto.domain.value.LottoNumber

class Lotto(
    val lottoNumbers: List<LottoNumber>,
) {
    init {
        require(lottoNumbers.size == LottoConstants.NUMBER_OF_LOTTO_NUMBERS) { ErrorMessages.INVALID_NUMBER_OF_LOTTO_NUMBERS }
        require(lottoNumbers.toSet().size == LottoConstants.NUMBER_OF_LOTTO_NUMBERS) { ErrorMessages.DUPLICATE_LOTTO_NUMBER }
    }

    fun contains(number: LottoNumber): Boolean = lottoNumbers.contains(number)

    fun count(lotto: Lotto): Int = lottoNumbers.count { lotto.contains(it) }

    companion object {
        fun of(vararg numbers: Int): Lotto = Lotto(numbers.map { LottoNumber.from(it) })

        fun create(): Lotto {
            val lottoNumbers =
                LottoNumber.lottoNumbers.values
                    .shuffled()
                    .take(
                        LottoConstants.NUMBER_OF_LOTTO_NUMBERS,
                    ).sortedBy { it.number }

//            val lottoNumbers =
//                (LottoConstants.MINIMUM_LOTTO_NUMBER..LottoConstants.MAXIMUM_LOTTO_NUMBER)
//                    .shuffled()
//                    .take(LottoConstants.NUMBER_OF_LOTTO_NUMBERS)
//                    .sorted()
//                    .map { LottoNumber.from(it) }
            return Lotto(lottoNumbers)
        }
    }
}
