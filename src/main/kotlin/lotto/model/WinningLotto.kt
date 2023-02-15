package lotto.model

class WinningLotto(val winningNumbers: Lotto, val bonusNumber: Int) {
    constructor(winningNumbers: List<Int>, bonusNumber: Int) : this(Lotto(winningNumbers), bonusNumber)
}
