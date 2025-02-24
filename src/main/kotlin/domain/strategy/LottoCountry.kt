package domain.strategy

import domain.model.LottoNumber

interface LottoCountry {
    fun generateNumber(): List<LottoNumber>
}
