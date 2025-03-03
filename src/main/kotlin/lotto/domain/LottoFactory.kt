package lotto.domain

import lotto.service.AutoLottoNumberGenerator
import lotto.service.ManualLottoNumberGenerator

class LottoFactory {
    fun generateAutoLotto(generator: AutoLottoNumberGenerator): Lotto {
        return Lotto(generator.generateLottoNumbers())
    }

    fun generateManualLotto(numbers: List<Int>): Lotto {
        val generator = ManualLottoNumberGenerator(numbers)
        return Lotto(generator.generateLottoNumbers())
    }
}
