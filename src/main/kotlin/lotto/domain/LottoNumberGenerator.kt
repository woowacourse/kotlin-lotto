package lotto.domain

import lotto.domain.model.LottoNumber

interface LottoNumberGenerator {
    fun generate(): List<LottoNumber>
}
