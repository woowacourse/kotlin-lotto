package domain.service

import domain.model.LottoNumber

fun interface LottoNumbersGenerator {
    fun makeLottoNumbers(): Set<LottoNumber>
}
