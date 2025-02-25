package lotto.service

import lotto.domain.LottoNumber

fun interface LottoNumberGenerator {
    fun generate(): List<LottoNumber>
}
