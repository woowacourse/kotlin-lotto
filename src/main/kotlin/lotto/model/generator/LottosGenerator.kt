package lotto.model.generator

import lotto.model.Lotto

interface LottosGenerator {
    fun generate(count: Int): List<Lotto>
}
