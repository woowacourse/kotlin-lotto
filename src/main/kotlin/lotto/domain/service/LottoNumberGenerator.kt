package lotto.domain.service

import lotto.domain.model.LottoNumber

interface LottoNumberGenerator {
    fun generate(): List<LottoNumber>
}
