package lotto.model

class LottoStore private constructor(val lottos: List<Lotto>) {
    companion object {
        fun buyLottos(
            purchaseInfo: PurchaseOrder,
            lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator,
        ): LottoStore {
            val lottos = List(purchaseInfo.amount) { Lotto.create(lottoNumberGenerator.generate()) }
            return LottoStore(lottos)
        }
    }
}
