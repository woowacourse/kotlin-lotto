package lotto.global

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.MAX_LOTTO_LENGTH
import lotto.domain.MAX_LOTTO_NUMBER
import lotto.domain.MIN_LOTTO_NUMBER
import lotto.domain.Rank
import lotto.domain.ScoreRankMap
import java.util.LinkedList

object LottoUtil {
    fun getLotto(): Lotto {
        // 애초에 이미 리스트를 만들고 래핑하는게 맞나? 이 로직을 lotto 내부로 숨겨야 할까?
        val lotto = mutableListOf<LottoNumber>()
        val range: LinkedList<Int> = LinkedList(IntRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER).shuffled())
        repeat(MAX_LOTTO_LENGTH) { lotto.add(LottoNumber.of(range.poll())) }

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

    fun getRate(scoreRankMap: ScoreRankMap): String {
        val earned = scoreRankMap.getEarned()
        val paid = scoreRankMap.getPaid()
        return if (paid == 0) "0.0" else String.format("%.2f", earned.toDouble() / paid.toDouble())
    }
}
