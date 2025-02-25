package lottoTest

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.Rank
import lotto.domain.WinningLottoTicket
import lotto.service.LottoRankFinder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoRankFinderTest {
    private val lottoRankFinder = LottoRankFinder()

    @Test
    @DisplayName("복권을 구매한 숫자만큼 복권의 당첨 등수를 반환한다")
    fun t1() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(7)
        val result = lottoRankFinder.findLottoRanks(manyLotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result.map[Rank.MISS]).isEqualTo(3)
    }

    @Test
    @DisplayName("복권을 구매한 숫자만큼 복권의 당첨 등수를 반환한다")
    fun t1_1() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 11, 10, 5, 6).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(7)
        val result = lottoRankFinder.findLottoRanks(manyLotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result.map[Rank.MISS]).isEqualTo(2)
        assertThat(result.map[Rank.FOURTH]).isEqualTo(1)
    }

    @Test
    @DisplayName("복권을 구매한 숫자만큼 복권의 당첨 등수를 반환한다")
    fun t1_2() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 11, 10, 5, 6).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }),
                Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber.of(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(7)
        val result = lottoRankFinder.findLottoRanks(manyLotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result.map[Rank.MISS]).isEqualTo(1)
        assertThat(result.map[Rank.FOURTH]).isEqualTo(1)
        assertThat(result.map[Rank.FIRST]).isEqualTo(1)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t2() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(7)
        val result = lottoRankFinder.findLottoRank(lotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result).isEqualTo(Rank.FIRST)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t2_1() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 8).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(7)
        val result = lottoRankFinder.findLottoRank(lotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t2_2() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(7)
        val result = lottoRankFinder.findLottoRank(lotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result).isEqualTo(Rank.SECOND)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t2_2_1() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(5)
        val result = lottoRankFinder.findLottoRank(lotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t2_3() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 8, 9).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(7)
        val result = lottoRankFinder.findLottoRank(lotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result).isEqualTo(Rank.FOURTH)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t2_4() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 10, 8, 9).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(7)
        val result = lottoRankFinder.findLottoRank(lotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result).isEqualTo(Rank.FIFTH)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t2_5() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) })
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber.of(it) })
        val bonus = LottoNumber.of(7)
        val result = lottoRankFinder.findLottoRank(lotto, WinningLottoTicket(winningLotto, bonus))
        assertThat(result).isEqualTo(Rank.MISS)
    }
}
