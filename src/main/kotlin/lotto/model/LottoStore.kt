package lotto.model

object LottoStore {
    fun buyAutoMaticLottos(
        purchaseOrder: PurchaseOrder,
        lottoMachine: LottoMachine,
    ): List<Lotto> {
        return List(purchaseOrder.automaticLottoSize) { Lotto.create(lottoMachine.generate()) }
    }

    fun combineLottos(
        manualLottos: List<Lotto>,
        automaticLottos: List<Lotto>,
    ): List<Lotto> {
        return manualLottos + automaticLottos
    }
}
