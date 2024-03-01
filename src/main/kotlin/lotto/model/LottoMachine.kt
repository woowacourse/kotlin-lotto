package lotto.model

class LottoMachine(expense: Money) {
    var availableCount: Count = Count.fromMoney(expense)
        private set

    private val _currentLotteries: MutableList<Lotto> = mutableListOf()
    val currentLotteries: List<Lotto> = _currentLotteries

    private val _autoLotteries: MutableList<Lotto> = mutableListOf()
    val autoLotteries: List<Lotto> = _autoLotteries

    lateinit var winningLotto: Lotto
        private set

    fun addManualLotteries(lotteryNumbers: List<List<Int>>) {
        val manualLotteries = lotteryNumbers.map { generateLotto(it) }
        addLotteries(manualLotteries)
        availableCount -= Count(lotteryNumbers.size)
    }

    fun addAutoLotteries(lotteryNumbers: List<List<Int>>) {
        val autoLotteries = lotteryNumbers.map { generateLotto(it) }
        addLotteries(autoLotteries)
        _autoLotteries += autoLotteries
    }

    fun setWinningLotto(winningNumbers: List<Int>) {
        winningLotto = Lotto(*winningNumbers.toIntArray())
    }

    private fun addLotteries(lotteries: List<Lotto>) {
        _currentLotteries += lotteries
    }

    private fun generateLotto(numbers: List<Int>): Lotto = Lotto(*numbers.toIntArray())
}
