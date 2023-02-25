package domain

class Lottos(val lottos: List<Lotto>) {

    constructor(vararg lottos: IntArray) : this(lottos.map { Lotto(*it) })

    operator fun plus(any: Lottos): Lottos = Lottos(lottos + any.lottos)
}
