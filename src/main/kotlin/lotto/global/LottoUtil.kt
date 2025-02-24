package lotto.global

import lotto.domain.Lotto
import lotto.global.Config.MAX_LOTTO_LENGTH
import lotto.global.Config.MAX_RANDOM_NUM
import lotto.global.Config.MIN_RANDOM_NUM
import java.util.LinkedList

object LottoUtil {
    fun getLotto(): Lotto {
        // 애초에 이미 리스트를 만들고 래핑하는게 맞나? 이 로직을 lotto 내부로 숨겨야 할까?
        val lotto = mutableListOf<Int>()
        val range: LinkedList<Int> = LinkedList(IntRange(MIN_RANDOM_NUM, MAX_RANDOM_NUM).shuffled())
        repeat(MAX_LOTTO_LENGTH) { lotto.add(range.poll()) }

        return Lotto(lotto.toList())
    }

    fun getLottoRank(
        lotto: Lotto,
        winningLotto: Lotto,
        bonus: Int,
    ): Rank {
        val countOfMatch = winningLotto.getCountOfMatchWith(lotto)
        val isBonusMatched = winningLotto.contains(bonus) && !lotto.contains(bonus)
        return Rank.getRank(countOfMatch, isBonusMatched)
    }
}
