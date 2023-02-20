package domain

class LottoBundle(lottoCount: Int, lottoGenerator: LottoGenerator) {
    val lottos: List<Lotto> = List(lottoCount) { lottoGenerator.generate() }
}
