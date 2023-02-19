package lotto.model

import lotto.view.ERROR_DUPLICATE_NUMBER

class WinningLotto(val winningNumbers: Lotto, val bonusNumber: LottoNumber) {

    init {
        require(hasNoDuplicateNumber()) { ERROR_DUPLICATE_NUMBER }
    }

    constructor(winningLotto: List<Int>, bonusNumber: Int) : this(Lotto.create(winningLotto), LottoNumber.create(bonusNumber))

    private fun hasNoDuplicateNumber(): Boolean {
        return !winningNumbers.isContained(bonusNumber)
    }
}
