package lotto.model

class LottoMachine(expense: Money) {
    var availableCount: Count = Count.from(expense)
        private set

    private val _currentLotteries: MutableList<Lotto> = mutableListOf()
    val currentLotteries: List<Lotto> = _currentLotteries

    private val _autoLotteries: MutableList<Lotto> = mutableListOf()
    val autoLotteries: List<Lotto> = _autoLotteries

    lateinit var winningLotto: Lotto
        private set

    fun addManualLotteries(manualLotteries: List<Lotto>) {
        addLotteries(manualLotteries)
        availableCount -= Count(manualLotteries.size)
    }

    fun addAutoLotteries(autoLotteries: List<Lotto>) {
        addLotteries(autoLotteries)
        _autoLotteries += autoLotteries
    }

    fun setWinningLotto(winningNumbers: List<Int>) {
        winningLotto = Lotto(*winningNumbers.toIntArray())
    }

    private fun addLotteries(lotteries: List<Lotto>) {
        _currentLotteries += lotteries
    }
}
