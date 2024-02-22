package lotto.model

data class LottoBundle(val lottos: List<Lotto>) {
    override fun toString(): String = lottos.joinToString("\n")
}
