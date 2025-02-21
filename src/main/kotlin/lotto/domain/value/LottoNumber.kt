package lotto.domain.value

import lotto.constants.ErrorMessages
import lotto.constants.LottoConstants

@JvmInline
value class LottoNumber private constructor(
    val number: Int,
) {
    companion object {
        private val lottoNumbers: Map<Int, LottoNumber> =
            (LottoConstants.MINIMUM_LOTTO_NUMBER..LottoConstants.MAXIMUM_LOTTO_NUMBER).associateWith {
                LottoNumber(it)
            }

        fun from(number: Int): LottoNumber = requireNotNull(lottoNumbers[number]) { ErrorMessages.INVALID_LOTTO_NUMBER_RANGE }
    }
}
