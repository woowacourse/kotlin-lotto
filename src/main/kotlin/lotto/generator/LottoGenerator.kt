package lotto.generator

import lotto.domain.Lotto

interface LottoGenerator {
    fun generateLotto(): List<Lotto>
}
