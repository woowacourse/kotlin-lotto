package lottoTest

import lotto.LottoService
import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoServiceTest {
    private val lottoService = LottoService()

    @Test
    @DisplayName("복권을 구매할 숫자를 입력받으면 입력받은 숫자만큼의 복권 정보를 반환한다")
    fun t2() {
        val manyLotto = lottoService.getManyLotto(15)
        assertThat(manyLotto).hasSize(15)
    }

    @Test
    @DisplayName("복권을 구매한 숫자만큼 복권의 당첨 등수를 반환한다")
    fun t4() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber(it) })
        val bonus = 7
        val result = lottoService.getLottoRankMany(manyLotto, winningLotto, bonus)
        assertThat(result.map[Rank.MISS]).isEqualTo(3)
    }

    @Test
    @DisplayName("복권을 구매한 숫자만큼 복권의 당첨 등수를 반환한다")
    fun t4_1() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 11, 10, 5, 6).map { LottoNumber(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber(it) })
        val bonus = 7
        val result = lottoService.getLottoRankMany(manyLotto, winningLotto, bonus)
        assertThat(result.map[Rank.MISS]).isEqualTo(2)
        assertThat(result.map[Rank.FOURTH]).isEqualTo(1)
    }

    @Test
    @DisplayName("복권을 구매한 숫자만큼 복권의 당첨 등수를 반환한다")
    fun t4_2() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 11, 10, 5, 6).map { LottoNumber(it) }),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }),
                Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber(it) }),
            )
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9).map { LottoNumber(it) })
        val bonus = 7
        val result = lottoService.getLottoRankMany(manyLotto, winningLotto, bonus)
        assertThat(result.map[Rank.MISS]).isEqualTo(1)
        assertThat(result.map[Rank.FOURTH]).isEqualTo(1)
        assertThat(result.map[Rank.FIRST]).isEqualTo(1)
    }
}
