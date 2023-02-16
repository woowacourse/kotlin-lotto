import domain.BonusNumber
import domain.Lotto
import domain.Lottos
import domain.Money
import domain.RandomLottoGenerator
import domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {

    private val testLottos = listOf(
        Lotto(listOf(1, 2, 3, 4, 5, 6)),
        Lotto(listOf(1, 2, 4, 6, 34, 45)),
        Lotto(listOf(3, 5, 15, 23, 33, 43)),
        Lotto(listOf(4, 6, 15, 18, 24, 35))
    )
    @Test
    fun `로또 리스트가 4개 생성되었는지 확인`() {
        val lottos = Lottos(testLottos)
        assertThat(lottos.lottos.size).isEqualTo(4)
    }

    @Test
    fun `금액 10000원을 입력하면 10개의 로또가 생성되는지 확인`() {
        val lottos = RandomLottoGenerator().generateLottos(Money(10000))
        assertThat(lottos.lottos.size).isEqualTo(10)
    }

    @Test
    fun `전체 당첨 결과 확인`() {
        val testLottos = Lottos(testLottos)
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 6, 10))
        val bonusNumber = BonusNumber(45)
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.FIRST]).isEqualTo(0)
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.SECOND]).isEqualTo(0)
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.THIRD]).isEqualTo(1)
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.FOURTH]).isEqualTo(1)
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.FIFTH]).isEqualTo(0)
    }
}
