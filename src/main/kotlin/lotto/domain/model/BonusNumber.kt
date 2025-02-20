package lotto.domain.model

import lotto.ERROR_PREFIX

class BonusNumber(winningNumbers: WinningNumbers, val number: LottoNumber) {
    init {
        require(winningNumbers.numbers.contains(number).not()) { DUPLICATE_BONUS_NUMBER_MESSAGE }
    }

    private companion object {
        const val DUPLICATE_BONUS_NUMBER_MESSAGE = "${ERROR_PREFIX}보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
