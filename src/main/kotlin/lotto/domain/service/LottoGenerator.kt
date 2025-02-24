package lotto.domain.service

import lotto.domain.model.Lotto

interface LottoGenerator {
    fun generate():Lotto
}
