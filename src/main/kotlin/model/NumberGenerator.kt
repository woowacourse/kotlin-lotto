package lotto.model

import model.LottoNumber

fun interface NumberGenerator {
    fun generate(): List<LottoNumber>
}
