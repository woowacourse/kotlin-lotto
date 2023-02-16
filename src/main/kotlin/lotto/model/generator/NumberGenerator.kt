package lotto.model.generator

import lotto.model.LottoNumber

interface NumberGenerator {
    fun generate(): List<LottoNumber>
}
