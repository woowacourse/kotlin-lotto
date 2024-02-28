package lotto.model

object LottoStore {
    fun buyAutoMaticLottos(
        automaticLottoSize: Int,
        lottoMachine: LottoMachine,
    ): List<Lotto> {
        return List(automaticLottoSize) { Lotto.create(lottoMachine.generate()) }
    }

    fun combineLottos(
        manualLottos: List<Lotto>,
        automaticLottos: List<Lotto>,
    ): List<Lotto> {
        return manualLottos + automaticLottos
    }
}
