package model

class Lottos(val lottos: List<Lotto>) {
    override fun toString(): String {
        return lottos.joinToString("\n")
    }
}
