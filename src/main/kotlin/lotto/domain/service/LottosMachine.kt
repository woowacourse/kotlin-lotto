package lotto.domain.service

import lotto.domain.model.Lotto

class LottosMachine(
    private val generator: LottoMachine = LottoMachineImpl(),
) {
    fun generate(count: Int): List<Lotto> {
        return List(count) { generator.generateRandomLottoNumbers() }
    }
}
