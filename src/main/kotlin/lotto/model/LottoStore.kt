package lotto.model

class LottoStore() {
    var lottos = Lottos()
        private set

    fun generateManualLottos(manualLottoNumbers: List<String>) {
        val lotto = Lotto.lottoNumbersOf(manualLottoNumbers)
        lottos.add(lotto)
    }

    fun generateAutoLottos(autoLottoCount: Int) {
        repeat(autoLottoCount) {
            val lotto = LottoNumberGeneratorManager.generate(LOTTO_NUMBER_SIZE, MIN_NUMBER, MAX_NUMBER)
            lottos.add(lotto)
        }
    }

    private fun calculateProfitAmount(winningResult: Map<WinningRank, Int>): Int =
        winningResult.entries.sumOf {
            it.key.prize * (it.value)
        }.toInt()

    fun calculateProfitRate(
        purchaseAmount: Int,
        winningResult: Map<WinningRank, Int>,
    ): Double {
        val profitAmount = calculateProfitAmount(winningResult)
        return (profitAmount / purchaseAmount).toDouble()
    }

    companion object {
        private const val LOTTO_NUMBER_SIZE = 6
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 45
    }
}
