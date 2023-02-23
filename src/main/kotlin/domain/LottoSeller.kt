package domain

import domain.lottogenerator.AutomaticLottoGenerator
import domain.lottogenerator.ManualLottoGenerator
import domain.model.lotto.Lotto

class LottoSeller(
    private val automaticLottoGenerator: AutomaticLottoGenerator = AutomaticLottoGenerator(),
    private val manualLottoGenerator: ManualLottoGenerator = ManualLottoGenerator(),
) {

    fun sellAutoMaticLottos(autoMaticLottosCount: Int): List<Lotto> =
        List(autoMaticLottosCount) { automaticLottoGenerator.generate() }

    fun sellManualLotto(inputManualNumbers: List<Int>): Lotto =
        manualLottoGenerator.generate(inputManualNumbers)
}
