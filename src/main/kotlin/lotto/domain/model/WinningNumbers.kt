package lotto.domain.model

import lotto.ERROR_PREFIX

class WinningNumbers(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) { INVALID_WINNING_NUMBER_SIZE_MESSAGE }
        require(numbers.size == numbers.distinct().size) { DUPLICATE_WINNING_NUMBER_MESSAGE }
    }

    private companion object {
        const val LOTTO_NUMBER_SIZE = 6
        const val INVALID_WINNING_NUMBER_SIZE_MESSAGE = "${ERROR_PREFIX}당첨 번호는 6개여야 합니다."
        const val DUPLICATE_WINNING_NUMBER_MESSAGE = "${ERROR_PREFIX}당첨 번호는 중복될 수 없습니다."
    }
}
