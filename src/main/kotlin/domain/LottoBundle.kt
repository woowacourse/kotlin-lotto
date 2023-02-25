package domain

class LottoBundle {
    private val _lottos: MutableList<Lotto> = mutableListOf()
    val lottos: List<Lotto>
        get() = _lottos.toList()

    fun manualGenerate(lotto: Lotto) {
        _lottos.add(lotto)
    }

    fun autoGenerate(lottoCount: Int, lottoGenerator: LottoGenerator) {
        _lottos.addAll(List(lottoCount) { lottoGenerator.generate() })
    }
}
