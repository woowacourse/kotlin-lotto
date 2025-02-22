package lotto.model

class Lottos(
    val lottoBundle: List<Lotto>,
) {
    val size: Int
        get() = lottoBundle.size
}
