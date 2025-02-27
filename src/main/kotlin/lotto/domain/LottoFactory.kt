package lotto.domain

import lotto.service.AutoLottoGenerator
import lotto.service.ManualLottoGenerator

class LottoFactory {
    fun generateAutoLotto(generator: AutoLottoGenerator): Lotto {
        return Lotto(generator.generate())
    }

    fun generateManualLotto(numbers: List<Int>): Lotto {
        val generator = ManualLottoGenerator(numbers)
        return Lotto(generator.generate())
    }
}
