package lotto.model

import lotto.controller.ManualLottoController
import lotto.entity.Lotto

class InputLottoGenerator(private val manualLottoController: ManualLottoController) : LottoGenerator {
    override fun generate(): Lotto {
        return manualLottoController.readLotto()
    }
}
