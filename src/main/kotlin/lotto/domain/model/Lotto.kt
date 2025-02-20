package lotto.domain.model

import lotto.constants.ErrorMessages
import lotto.constants.LottoConstants
import lotto.domain.value.LottoNumber

class Lotto(
    val lottoNumbers: List<LottoNumber>,
) {
    init {
        require(lottoNumbers.size == LottoConstants.NUMBER_OF_LOTTO_NUMBERS) { ErrorMessages.INVALID_NUMBER_OF_LOTTO_NUMBERS }
        require(
            lottoNumbers
                .map { it.number }
                .distinct()
                .size == LottoConstants.NUMBER_OF_LOTTO_NUMBERS,
        ) { ErrorMessages.DUPLICATE_LOTTO_NUMBER }
        require(lottoNumbers.map { it.number }.zipWithNext().all { (a, b) -> a < b })
    }
}
