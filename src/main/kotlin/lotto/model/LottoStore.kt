package lotto.model

class LottoStore private constructor(val lottos: List<Lotto>) {
    companion object {
        fun buyLottos(
            purchaseOrder: PurchaseOrder,
            lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator,
        ): LottoStore {
            val lottos = List(purchaseOrder.amount) { Lotto.create(lottoNumberGenerator.generate()) }
            return LottoStore(lottos)
        }
    }
}
