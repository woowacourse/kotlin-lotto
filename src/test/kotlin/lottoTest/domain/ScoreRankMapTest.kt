package lottoTest.domain

import lotto.LottoRankFinder
import lotto.domain.Lotto
import lotto.domain.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ScoreRankMapTest {
    private val lottoRankFinder = LottoRankFinder()

    @Test
    @DisplayName("복권의 구매액과 총 당첨 정보를 입력받아 수익률을 반환한다")
    fun t1() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 3, 4, 44, 45).map { LottoNumber.of(it) }),
                Lotto(listOf(11, 12, 13, 14, 15, 16).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val bonus = 7
        val rankMap = lottoRankFinder.findLottoRanks(manyLotto, winningLotto, bonus)
        val result = rankMap.getRate()
        assertThat(result).isEqualTo("25.00")
    }

    @Test
    @DisplayName("수익률의 기준은 (수익금) / (구매액) 을 소수 둘 째 자리까지 반올림한 값이다")
    fun t1_1() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 3, 43, 44, 45).map { LottoNumber.of(it) }),
                Lotto(listOf(11, 12, 13, 14, 15, 16).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 43, 44, 45).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val bonus = 7
        val rankMap = lottoRankFinder.findLottoRanks(manyLotto, winningLotto, bonus)
        val result = rankMap.getRate()
        assertThat(result).isEqualTo("3.33")
    }
}
