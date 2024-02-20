package lotto.model
import lotto.util.Constant

object LottoGenerator{
    private val lottos = listOf(Constant.LOTTO_NUM_RANGE)

    fun generateLotto(): Set<IntRange> {
        return lottos.shuffled().take(Constant.LOTTO_LEN).toSet()
    }
}