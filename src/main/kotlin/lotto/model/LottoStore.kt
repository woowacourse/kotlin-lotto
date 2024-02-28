package lotto.model

object LottoStore {
    fun buyAutoMaticLottos(
        purchaseOrder: PurchaseOrder,
        lottoMachine: LottoMachine,
    ): List<Lotto> {
        val automaticLottoSize = purchaseOrder.getAutomaticLottoSize()
        return List(automaticLottoSize) { Lotto.create(lottoMachine.generate()) }
    }

    fun combineLottos(
        manualLottos: List<Lotto>,
        automaticLottos: List<Lotto>,
    ): List<Lotto> {
        return manualLottos + automaticLottos
    }
}
