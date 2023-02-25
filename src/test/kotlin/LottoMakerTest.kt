import domain.AllTypeLottoGenerator
import domain.Lotto
import domain.LottoGenerator
import domain.LottoMaker
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMakerTest {
    val lottoMaker = LottoMaker(AllTypeLottoGenerator())
    @Test
    fun `사용자가 입력한 대로 수동 로또가 생성되는지 확인`() {
        val numbers = listOf(listOf(1, 2, 3, 4, 5, 6), listOf(2, 3, 4, 5, 6, 7), listOf(3, 4, 5, 6, 7, 8))
        val manualLottos = lottoMaker.makeManualLottos(numbers)
        assertThat(manualLottos.map { it.numbers.map { it.getNumber() } }).isEqualTo(numbers)
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

        override fun generateLottos(manualLottos: List<List<Int>>): List<Lotto> {
            return listOf(Lotto(1, 2, 3, 4, 5, 6), Lotto(2, 3, 4, 5, 6, 7), Lotto(3, 4, 5, 6, 7, 8))
        }
    }
}
