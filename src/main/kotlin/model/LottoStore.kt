package lotto.model

import model.Lotto
import model.LottoNumber

class LottoStore(private val numberOfLottos: Int, private val lottoNumberGenerator: NumberGenerator) {
    var lottos = Lottos()
        private set

    init {
        generateLottos()
    }

    private fun generateLottos() {
        repeat(numberOfLottos) {
            val lotto = Lotto(lottoNumberGenerator.generate())
            lottos.add(lotto)
        }
    }

    fun getWinningResult(
        winningNumbers: Lotto,
        bonusNumber: LottoNumber,
    ): Map<WinningRank, Int> {
        return lottos.winningResult(winningNumbers, bonusNumber)
    }
}
