package lotto.domain.generator

import lotto.domain.model.LottoNumber

interface LottoNumbersGenerator {
    fun generate(): Set<LottoNumber>
}
