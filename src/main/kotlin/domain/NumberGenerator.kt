package domain

import model.LottoNumber

interface NumberGenerator {
    fun generate(): List<LottoNumber>
}
