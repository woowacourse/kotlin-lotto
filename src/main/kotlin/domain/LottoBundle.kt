package domain

class LottoBundle {
    private val _lottos: MutableList<Lotto> = mutableListOf()
    val lottos: List<Lotto>
        get() = _lottos.toList()

    fun manualGenerate(lottoNumbers: List<String>) {
        _lottos.add(Lotto(lottoNumbers))
    }

    fun autoGenerate(lottoCount: Int, lottoGenerator: LottoGenerator) {
        _lottos.addAll(List(lottoCount) { lottoGenerator.generate() })
    }
}
