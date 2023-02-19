import domain.BonusNumber
import domain.Lotto
import domain.LottoGenerator
import domain.LottoNumber
import domain.Lottos
import domain.Money
import domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `사용자가 원하는 개수 만큼 로또가 생성되는지 확인`() {
        val lottos = Lottos(testLottos)
        assertThat(lottos.lottos.size).isEqualTo(4)
    }

    @Test
    fun `금액 4000원을 입력하면 4개의 로또가 생성되는지 확인`() {
        val lottos = TestLottoGenerator().generateLottos(Money(4000))
        assertThat(lottos.lottos.size).isEqualTo(4)
    }

    @Test
    fun `전체 당첨 결과 확인`() {
        // given
        // Lottos = 1,2,3,4,5,6 / 1,2,4,6,34,45 / 3,5,15,18,24,35 / 4,6,15,18,24,35
        // Lottos = 1,2,3,4,6,10
        // bonusNumber = 45
        val testLottos = Lottos(testLottos)
        val winningLotto = Lotto(
            listOf(
                LottoNumber.from(1), LottoNumber.from(2),
                LottoNumber.from(3), LottoNumber.from(4),
                LottoNumber.from(6), LottoNumber.from(10)
            )
        )
        val bonusNumber = BonusNumber(LottoNumber.from(45))

        // When
        val result = testLottos.matchLottos(winningLotto, bonusNumber).result

        // Then
        assertThat(result[Rank.FIRST]).isEqualTo(0) // 1등 당첨 0개
        assertThat(result[Rank.SECOND]).isEqualTo(0) // 2등 당첨 0개
        assertThat(result[Rank.THIRD]).isEqualTo(1) // 3등 당첨 1개
        assertThat(result[Rank.FOURTH]).isEqualTo(1) // 4등 당첨 1개
        assertThat(result[Rank.FIFTH]).isEqualTo(0) // 5등 당첨 0개
    }

    class TestLottoGenerator() : LottoGenerator {
        override fun generateLottos(money: Money): Lottos {
            return Lottos(testLottos)
        }
    }

    companion object {
        private val testLottos = listOf(
            Lotto(
                listOf(
                    LottoNumber.from(1), LottoNumber.from(2),
                    LottoNumber.from(3), LottoNumber.from(4),
                    LottoNumber.from(5), LottoNumber.from(6)
                )
            ),
            Lotto(
                listOf(
                    LottoNumber.from(1), LottoNumber.from(2),
                    LottoNumber.from(4), LottoNumber.from(6),
                    LottoNumber.from(34), LottoNumber.from(45)
                )
            ),
            Lotto(
                listOf(
                    LottoNumber.from(3), LottoNumber.from(5),
                    LottoNumber.from(15), LottoNumber.from(18),
                    LottoNumber.from(24), LottoNumber.from(35)
                )
            ),
            Lotto(
                listOf(
                    LottoNumber.from(4), LottoNumber.from(6),
                    LottoNumber.from(15), LottoNumber.from(18),
                    LottoNumber.from(24), LottoNumber.from(35)
                )
            )
        )
    }
}
