package lotto.model

object LottoStore {
    fun buyAutoMaticLottos(
        purchaseOrder: PurchaseOrder,
        lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator,
    ): List<Lotto> {
        val automaticLottoSize = purchaseOrder.getAutomaticLottoSize()
        return List(automaticLottoSize) { Lotto.create(lottoNumberGenerator.generate()) }
    }

    fun combineLottos(
        manualLottos: List<Lotto>,
        automaticLottos: List<Lotto>,
    ): List<Lotto> {
        return manualLottos + automaticLottos
    }
}
