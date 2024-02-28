package lotto.model

class LottoStore(private val lottoNumberGenerator: NumberGenerator) {
    var lottos = Lottos()
        private set

    fun generateManualLottos(manualLottos: Lotto) {
        lottos.add(manualLottos)
    }

    fun generateAutoLottos(autoLottoCount: Int) {
        repeat(autoLottoCount) {
            val lotto = lottoNumberGenerator.generate(LOTTO_NUMBER_SIZE, MIN_NUMBER, MAX_NUMBER)
            lottos.add(lotto)
        }
    }

    fun getWinningResult(winningLotto: WinningLotto): Map<WinningRank, Int> {
        return lottos.winningResult(winningLotto)
    }

    companion object {
        private const val LOTTO_NUMBER_SIZE = 6
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 45
    }
}
