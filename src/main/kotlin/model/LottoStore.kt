package lotto.model

import lotto.constants.GameConstant.LOTTO_NUMBER_SIZE
import lotto.constants.GameConstant.MAX_NUMBER
import lotto.constants.GameConstant.MIN_NUMBER
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
            val lotto = lottoNumberGenerator.generate(LOTTO_NUMBER_SIZE, MIN_NUMBER, MAX_NUMBER)
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
