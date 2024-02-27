package lotto.model

class LottoStore private constructor(val lottos: List<Lotto>) {
    companion object {
        fun buyLottos(
            manualLottos: List<Lotto>,
            purchaseOrder: PurchaseOrder,
            lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator,
        ): LottoStore {
            val automaticLottoSize = getAutomaticLottoSize(manualLottos, purchaseOrder)
            val lottos = List(automaticLottoSize) { Lotto.create(lottoNumberGenerator.generate()) }
            return LottoStore(manualLottos + lottos)
        }

        private fun getAutomaticLottoSize(
            manualLottos: List<Lotto>,
            purchaseOrder: PurchaseOrder,
        ): Int {
            return purchaseOrder.amount - manualLottos.size
        }
    }
}
