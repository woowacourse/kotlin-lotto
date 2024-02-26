package lotto

import lotto.controller.LottoController
import lotto.model.RandomLottoNumberGenerator

fun main() {
    LottoController(RandomLottoNumberGenerator).run()
}
