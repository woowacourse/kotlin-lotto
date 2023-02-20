package lotto.model

import lotto.view.ERROR_DUPLICATE_NUMBER

class WinningLotto(val winningNumbers: Lotto, val bonusNumber: LottoNumber) {

    init {
        require(hasNoDuplicateNumber()) { ERROR_DUPLICATE_NUMBER }
    }

    constructor(winningLotto: IntArray, bonusNumber: Int) : this(Lotto(*winningLotto), LottoNumber.from(bonusNumber))

    private fun hasNoDuplicateNumber(): Boolean {
        return !winningNumbers.isContained(bonusNumber)
    }
}
