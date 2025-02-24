package lottoTest.global

import lotto.domain.Lotto
import lotto.global.LottoUtil
import lotto.global.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoUtilTest {
    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.FIRST)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_1() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 8))
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_2() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.SECOND)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_2_1() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val bonus = 5
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_3() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 8, 9))
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.FOURTH)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_4() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = Lotto(listOf(1, 2, 3, 10, 8, 9))
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.FIFTH)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_5() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9))
        val bonus = 7
        val result = LottoUtil.getLottoRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.MISS)
    }
}
