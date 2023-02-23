package domain.lottogenerator

import domain.model.WinningLotto
import domain.model.lotto.LottoNumber

class WinningLottoGenerator(
    private val manualLottoGenerator: ManualLottoGenerator = ManualLottoGenerator()
) {

    fun generateWinningLotto(inputNumbers: List<Int>, bonusNumber: Int): WinningLotto =
        WinningLotto(manualLottoGenerator.generate(inputNumbers), LottoNumber.from(bonusNumber))
}
