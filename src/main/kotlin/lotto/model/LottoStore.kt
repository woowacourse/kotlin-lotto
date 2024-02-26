package lotto.model

class LottoStore private constructor(val lottos: List<Lotto>) {
    val size = lottos.size

    fun forEach(action: (Lotto) -> Unit) = lottos.forEach(action)

    companion object {
        fun create(
            purchaseInfo: PurchaseOrder,
            lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator,
        ): LottoStore {
            val lottos = List(purchaseInfo.amount) { Lotto(lottoNumberGenerator.generate()) }
            return LottoStore(lottos)
        }
    }
}
