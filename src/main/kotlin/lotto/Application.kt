package lotto

import lotto.controller.LottoController
import lotto.service.RandomLottoNumberGenerator

fun main() {
    LottoController(RandomLottoNumberGenerator).run()
}
