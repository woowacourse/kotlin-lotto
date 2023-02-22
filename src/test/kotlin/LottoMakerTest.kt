import domain.Lotto
import domain.LottoGenerator
import domain.LottoMaker
import domain.Lottos
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMakerTest {
    @Test
    fun `사용자가 입력한 대로 수동 로또가 생성되는지 확인`() {
        val numbers = listOf(Lotto(1, 2, 3, 4, 5, 6), Lotto(2, 3, 4, 5, 6, 7), Lotto(3, 4, 5, 6, 7, 8))
        val manualLottos = LottoMaker().makeManualLottos(numbers)
        assertThat(manualLottos.lottos).isEqualTo(Lottos(numbers).lottos)
    }

    @Test
    fun `사용자가 원하는 수만큼 자동 로또가 생성되는지 확인`() {
        val autoLottos = TestLottoGenerator().generateLottos(2)
        assertThat(autoLottos.size).isEqualTo(2)
    }

    class TestLottoGenerator() : LottoGenerator {
        override fun generateLottos(count: Int): List<Lotto> {
            return listOf(Lotto(1, 2, 3, 4, 5, 6), Lotto(2, 3, 4, 5, 6, 7))
        }
    }
}
