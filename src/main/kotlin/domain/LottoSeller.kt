package domain

import domain.lottogenerator.AutomaticLottoGenerator
import domain.lottogenerator.ManualLottoGenerator
import domain.model.PurchaseMoney

class LottoSeller(
    private val automaticLottoGenerator: AutomaticLottoGenerator = AutomaticLottoGenerator(),
    private val manualLottoGenerator: ManualLottoGenerator = ManualLottoGenerator()
) {

}
