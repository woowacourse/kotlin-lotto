package lotto.model

import lotto.view.ERROR_DUPLICATE_NUMBER

class WinningLotto(val winningNumbers: Lotto, val bonusNumber: Int) {

    init {
        require(hasNoDuplicateNumber()) { ERROR_DUPLICATE_NUMBER }
    }

    constructor(winningNumbers: List<Int>, bonusNumber: Int) : this(Lotto(winningNumbers), bonusNumber)

    fun hasNoDuplicateNumber(): Boolean {
        return !winningNumbers.lotto.contains(bonusNumber)
    }
}
