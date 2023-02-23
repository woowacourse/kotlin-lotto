package domain

class LottoBundle(manualLottos: MutableList<Lotto>) {
    private val _lottos: MutableList<Lotto> = manualLottos
    val lottos: List<Lotto>
        get() = _lottos.toList()

    fun autoGenerate(lottoCount: Int, lottoGenerator: LottoGenerator) {
        _lottos.addAll(List(lottoCount) { lottoGenerator.generate() })
    }
}
