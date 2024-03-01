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

    fun addManualLotteries(lottoNumbers: List<List<Int>>) {
        val manualLotteries = lottoNumbers.map { generateLotto(it) }
        addLotteries(manualLotteries)
        availableCount -= Count(lottoNumbers.size)
    }

    fun addAutoLotteries(lottoNumbers: List<List<Int>>) {
        val autoLotteries = lottoNumbers.map { generateLotto(it) }
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
