import domain.BonusNumber
import domain.Lotto
import domain.LottoNumber
import domain.Lottos
import domain.Money
import domain.RandomLottoGenerator
import domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
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
        val winningLotto = Lotto(
            listOf(
                LottoNumber.from(1), LottoNumber.from(2),
                LottoNumber.from(3), LottoNumber.from(4),
                LottoNumber.from(6), LottoNumber.from(10)
            )
        )
        val bonusNumber = BonusNumber(LottoNumber.from(45))
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.FIRST]).isEqualTo(0)
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.SECOND]).isEqualTo(0)
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.THIRD]).isEqualTo(1)
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.FOURTH]).isEqualTo(1)
        assertThat(testLottos.matchLottos(winningLotto, bonusNumber).result[Rank.FIFTH]).isEqualTo(0)
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
