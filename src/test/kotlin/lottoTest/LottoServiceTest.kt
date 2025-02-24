package lottoTest

import lotto.LottoService
import lotto.domain.Lotto
import lotto.global.Rank
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
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
            )
        val winningLotto = Lotto(listOf(1, 2, 11, 10, 8, 9))
        val bonus = 7
        val result = lottoService.getLottoRankMany(manyLotto, winningLotto, bonus)
        assertThat(result.map[Rank.MISS]).isEqualTo(3)
    }

    @Test
    @DisplayName("복권의 구매액과 총 당첨 정보를 입력받아 수익률을 반환한다")
    fun t5() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 3, 4, 44, 45)),
                Lotto(listOf(11, 12, 13, 14, 15, 16)),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = 7
        val rankMap = lottoService.getLottoRankMany(manyLotto, winningLotto, bonus)
        val result = LottoService.getRate(rankMap)
        assertThat(result).isEqualTo("25.00")
    }

    @Test
    @DisplayName("수익률의 기준은 (수익금) / (구매액) 을 소수 둘 째 자리까지 반올림한 값이다")
    fun t5_1() {
        val manyLotto =
            listOf(
                Lotto(listOf(1, 2, 3, 43, 44, 45)),
                Lotto(listOf(11, 12, 13, 14, 15, 16)),
                Lotto(listOf(1, 2, 3, 43, 44, 45)),
            )
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = 7
        val rankMap = lottoService.getLottoRankMany(manyLotto, winningLotto, bonus)
        val result = LottoService.getRate(rankMap)
        assertThat(result).isEqualTo("3.33")
    }
}
