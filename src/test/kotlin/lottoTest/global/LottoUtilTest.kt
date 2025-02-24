package lottoTest.global

import lotto.LottoService
import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.Rank
import lotto.global.LottoUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoUtilTest {
    private val lottoService = LottoService()

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.FIRST)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_1() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 8).map { LottoNumber.of(it) })
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_2() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber.of(it) })
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.SECOND)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_2_1() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber.of(it) })
        val bonus = 5
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_3() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 8, 9).map { LottoNumber.of(it) })
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.FOURTH)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_4() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 10, 8, 9).map { LottoNumber.of(it) })
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.FIFTH)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_5() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber.of(it) })
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.MISS)
    }

    @Test
    @DisplayName("복권의 구매액과 총 당첨 정보를 입력받아 수익률을 반환한다")
    fun t5() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 3, 4, 44, 45).map { LottoNumber.of(it) }),
                Lotto(listOf(11, 12, 13, 14, 15, 16).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val bonus = 7
        val rankMap = lottoService.getLottoRankMany(manyLotto, winningLotto, bonus)
        val result = LottoUtil.getRate(rankMap)
        assertThat(result).isEqualTo("25.00")
    }

    @Test
    @DisplayName("수익률의 기준은 (수익금) / (구매액) 을 소수 둘 째 자리까지 반올림한 값이다")
    fun t5_1() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 3, 43, 44, 45).map { LottoNumber.of(it) }),
                Lotto(listOf(11, 12, 13, 14, 15, 16).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 43, 44, 45).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val bonus = 7
        val rankMap = lottoService.getLottoRankMany(manyLotto, winningLotto, bonus)
        val result = LottoUtil.getRate(rankMap)
        assertThat(result).isEqualTo("3.33")
    }
}
