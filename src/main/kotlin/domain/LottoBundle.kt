package domain

class LottoBundle(private val _lottos: MutableList<Lotto>) {
    val lottos: List<Lotto>
        get() = _lottos.toList()

    fun autoGenerate(lottoCount: Int, lottoGenerator: LottoGenerator) {
        _lottos.addAll(List(lottoCount) { lottoGenerator.generate() })
    }
}
