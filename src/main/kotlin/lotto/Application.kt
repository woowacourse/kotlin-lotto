package lotto

import lotto.domain.factory.ManualLottoFactory
import lotto.domain.factory.RandomLottoFactory

fun main() {
    LottoController(manualLottoFactory = ManualLottoFactory(), autoLottoFactory = RandomLottoFactory()).runLotto()
}
