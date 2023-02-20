package domain.lottogenerator

import domain.model.WinningLotto
import domain.model.lotto.LottoNumber

class WinningLottoGenerator(
    private val manualLottoGenerator: ManualLottoGenerator = ManualLottoGenerator()
) {

    fun generateWinningLotto(InputNumbers: List<Int>, bonusNumber: Int): WinningLotto =
        WinningLotto(manualLottoGenerator.generate(InputNumbers), LottoNumber.from(bonusNumber))
}
