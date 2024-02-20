package lotto.model


object LottoGenerator{
    private val lottos = listOf(1..45)

    fun generateLotto(): Set<IntRange> {
        return lottos.shuffled().take(6).toSet()
    }


}