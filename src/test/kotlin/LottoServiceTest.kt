import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.random.Random

class LottoServiceTest {
    private val lottoService = LottoService(Random(284582))

    @Test
    @DisplayName("복권은 6개의 1부터 45까지의 중복되지 않은 랜덤 숫자로 이루어진 리스트이다")
    fun t1() {
        //랜덤 요소 안정성을 위하여 여러 번 반복합니다
        repeat(1000) {
            val lotto = lottoService.getLotto()
            assertThat(lotto)
                .hasSize(6)
                .allMatch({num-> num in 1..45 })
                .isEqualTo(lotto.toSet().toList())
        }
    }

    @Test
    @DisplayName("복권을 구매할 숫자를 입력받으면 입력받은 숫자만큼의 복권 정보를 반환한다")
    fun t2() {
        val manyLotto = lottoService.getManyLotto(15)
        assertThat(manyLotto).hasSize(15)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3() {
        val lotto = listOf(1,2,3,4,5,6)
        val winningLotto = listOf(1,2,3,4,5,6)
        val bonus = 7
        val result = lottoService.checkRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.FIRST)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_1() {
        val lotto = listOf(1,2,3,4,5,6)
        val winningLotto = listOf(1,2,3,4,5,5)
        val bonus = 7
        val result = lottoService.checkRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.THIRD)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_2() {
        val lotto = listOf(1,2,3,4,5,6)
        val winningLotto = listOf(1,2,3,4,5,7)
        val bonus = 7
        val result = lottoService.checkRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.SECOND)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_3() {
        val lotto = listOf(1,2,3,4,5,6)
        val winningLotto = listOf(1,2,3,4,8,9)
        val bonus = 7
        val result = lottoService.checkRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.FOURTH)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_4() {
        val lotto = listOf(1,2,3,4,5,6)
        val winningLotto = listOf(1,2,3,10,8,9)
        val bonus = 7
        val result = lottoService.checkRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.FIFTH)
    }

    @Test
    @DisplayName("지난 주 당첨 번호와 보너스 볼을 입력받으면 복권의 당첨 등수(enum 멤버)를 반환한다")
    fun t3_5() {
        val lotto = listOf(1,2,3,4,5,6)
        val winningLotto = listOf(1,2,11,10,8,9)
        val bonus = 7
        val result = lottoService.checkRank(lotto, winningLotto, bonus)
        assertThat(result).isEqualTo(Rank.MISS)
    }

    @Test
    @DisplayName("복권을 구매한 숫자만큼 복권의 당첨 등수를 반환한다")
    fun t4() {
        val manyLotto = listOf(
            listOf(1,2,3,4,5,6),
            listOf(1,2,3,4,5,6),
            listOf(1,2,3,4,5,6)
        )
        val winningLotto = listOf(1,2,11,10,8,9)
        val bonus = 7
        val result = lottoService.checkRankMany(manyLotto, winningLotto, bonus)
        assertThat(result[Rank.MISS]).isEqualTo(3)
    }

    @Test
    @DisplayName("복권의 구매액과 총 당첨 정보를 입력받아 수익률을 반환한다")
    fun t5 () {
        val manyLotto = listOf(
            listOf(1,2,3,4,44,45),
            listOf(11,12,13,14,15,16),
        )
        val winningLotto = listOf(1,2,3,4,5,6)
        val bonus = 7
        val rankMap = lottoService.checkRankMany(manyLotto, winningLotto, bonus)
        val result = LottoService.getRate(rankMap)
        assertThat(result).isEqualTo("25.00")
    }
}