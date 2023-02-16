package lotto.model

import lotto.view.ERROR_DUPLICATE_NUMBER

class WinningLotto(val winningNumbers: Lotto, val bonusNumber: LottoNumber) {

    init {
        require(hasNoDuplicateNumber()) { ERROR_DUPLICATE_NUMBER }
    }

    constructor(winningLotto: IntArray, bonusNumber: Int) : this(Lotto(*winningLotto), LottoNumber(bonusNumber))

    fun hasNoDuplicateNumber(): Boolean {
        return !winningNumbers.lotto.contains(bonusNumber)
    }
}
