package lotto.model

data class LottoBundle(val lottos: List<Lotto>) {
    override fun toString(): String = lottos.joinToString("\n")

    fun append(lottoBundle: LottoBundle): LottoBundle = copy(lottos = lottos + lottoBundle.lottos)
}
