package lotto.domain.model

import lotto.ERROR_PREFIX
import lotto.LOTTO_NUMBER_SIZE

open class LottoNumbers(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) { INVALID_LOTTO_NUMBER_SIZE_MESSAGE }
        require(numbers.size == numbers.distinct().size) { DUPLICATE_WINNING_NUMBER_MESSAGE }
    }

    private companion object {
        const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "${ERROR_PREFIX}로또 번호는 6개여야 합니다."
        const val DUPLICATE_WINNING_NUMBER_MESSAGE = "${ERROR_PREFIX}당첨 번호는 중복될 수 없습니다."
    }
}
