package lotto.model

data class Lottos(val publishedLottos: List<Lotto>) {
    override fun toString(): String {
        return publishedLottos.joinToString("\n")
    }

    operator fun plus(other: Lottos): Lottos {
        return Lottos(this.publishedLottos + other.publishedLottos)
    }
}
