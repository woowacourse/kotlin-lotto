package lotto.model

import lotto.view.ERROR_DUPLICATE_NUMBER
import lotto.view.ERROR_OUT_OF_RANGE

class WinningLotto(val winningNumbers: Lotto, val bonusNumber: Int) {

    init {
        require(hasNoDuplicateNumber()) { ERROR_DUPLICATE_NUMBER }
        require(bonusNumber in 1..45) { ERROR_OUT_OF_RANGE }
    }

    constructor(winningNumbers: List<Int>, bonusNumber: Int) : this(Lotto(winningNumbers), bonusNumber)

    fun hasNoDuplicateNumber(): Boolean {
        return !winningNumbers.lotto.contains(bonusNumber)
    }
}
