package model

class Lottos(val publishedLottos: List<Lotto>) {
    override fun toString(): String {
        return publishedLottos.joinToString("\n")
    }
}
