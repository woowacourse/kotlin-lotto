package domain

import domain.lottogenerator.AutomaticLottoGenerator
import domain.lottogenerator.ManualLottoGenerator

class LottoSeller(
    private val automaticLottoGenerator: AutomaticLottoGenerator = AutomaticLottoGenerator(),
    private val manualLottoGenerator: ManualLottoGenerator = ManualLottoGenerator(),
) {

    fun sellAutoMaticLottos(autoMaticLottosCount: Int) =
        List(autoMaticLottosCount) { automaticLottoGenerator.generate() }

    fun sellManualLotto(inputManualNumbers: List<Int>) =
        manualLottoGenerator.generate(inputManualNumbers)
}
