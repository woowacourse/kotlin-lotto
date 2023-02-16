package lotto.model

import lotto.view.ERROR_DUPLICATE_NUMBER

class WinningLotto(val winningNumbers: Lotto, val bonusNumber: LottoNumber) {

    init {
        require(hasNoDuplicateNumber()) { ERROR_DUPLICATE_NUMBER }
    }

    constructor(winningNumbers: List<LottoNumber>, bonusNumber: Int) : this(Lotto(winningNumbers), LottoNumber(bonusNumber))

    fun hasNoDuplicateNumber(): Boolean {
        return !winningNumbers.lotto.contains(bonusNumber)
    }
}
