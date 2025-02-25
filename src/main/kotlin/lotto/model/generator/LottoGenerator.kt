package lotto.model.generator

import lotto.model.Lotto

interface LottoGenerator {
    fun generate(count: Int): List<Lotto>
}
