package lotto.domain.service

import lotto.domain.model.Lotto

class LottosMachine(
    private val generator: LottoMachine = LottoMachineImpl(),
) {
    fun generateRandomLottos(count: Int): List<Lotto> {
        return List(count) { generator.generateRandomLottoNumbers() }
    }
}
