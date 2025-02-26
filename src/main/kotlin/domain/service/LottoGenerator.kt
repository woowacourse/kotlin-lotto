package domain.service

import domain.model.Lotto

interface LottoGenerator {
    fun getLottoNumbers(): Set<Int>

    fun makeLotto(): Lotto

    fun makeLottos(amount: Int): List<Lotto>
}
