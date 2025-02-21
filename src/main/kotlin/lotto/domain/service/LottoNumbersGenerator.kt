package lotto.domain.service

import lotto.domain.model.LottoNumber

interface LottoNumbersGenerator {
    fun generate(): Set<LottoNumber>
}
