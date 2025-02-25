package lotto.domain.service

import lotto.domain.model.Lotto

interface LottoMachine {
    fun generate(
        count: Int,
        lottoNumbers: List<List<Int>>? = null,
    ): List<Lotto>
}
