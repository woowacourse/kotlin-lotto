package lotto.domain

import lotto.domain.model.LottoNumber

interface LottoNumberGenerator {
    fun generate(count: Int): List<List<LottoNumber>>
}
