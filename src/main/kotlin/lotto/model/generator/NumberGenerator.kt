package lotto.model.generator

import lotto.model.Lotto

interface NumberGenerator {
    fun generate(): Lotto
}
