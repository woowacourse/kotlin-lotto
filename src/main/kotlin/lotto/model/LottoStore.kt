package lotto.model

object LottoStore {
    fun buyLottos(
        lottoSize: Int,
        lottoMachine: LottoMachine,
    ): List<Lotto> {
        return List(lottoSize) { Lotto.create(lottoMachine.generate()) }
    }
}
