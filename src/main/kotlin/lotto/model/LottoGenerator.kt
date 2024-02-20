package lotto.model
import lotto.util.Constant

class LottoGenerator(){
    fun generateLotto(): Set<IntRange> {
        return lottos.shuffled().take(Constant.LOTTO_LEN).toSet()
    }

    companion object{
        private val lottos = listOf(Constant.LOTTO_NUM_RANGE)
    }
}