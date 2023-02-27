package lotto

import lotto.domain.factory.ManualLottoFactory
import lotto.domain.factory.RandomLottoFactory

fun main() {
    LottoController(RandomLottoFactory(), ManualLottoFactory()).runLotto()
}
