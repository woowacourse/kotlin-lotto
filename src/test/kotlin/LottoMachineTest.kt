import domain.Lotto
import domain.LottoGenerator
import domain.LottoMachine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LottoMachineTest {

    @Test
    fun `자동로또 2개가 잘 생성되었는지 확인`() {
        val lottos = LottoMachine.generateAutoLottos(2, TestLottosGenerator())
        assertThat(lottos.size).isEqualTo(2)
    }

    @Test
    fun `수동로또가 입력한 대로 잘 생성되었는지 확인`() {
        val numbers =
            listOf(intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(3, 4, 5, 6, 7, 8), intArrayOf(9, 10, 11, 12, 13, 14))
        val manualLottos = LottoMachine.generateManualLottos(numbers)
        assertAll({
            assertThat(manualLottos.size).isEqualTo(3)
            assertThat(manualLottos.toList().map { it.toList().map { it.number } }).isEqualTo(
                listOf(
                    listOf(1, 2, 3, 4, 5, 6),
                    listOf(3, 4, 5, 6, 7, 8),
                    listOf(9, 10, 11, 12, 13, 14),
                ),
            )
        })
    }

    class TestLottosGenerator : LottoGenerator {
        override fun generateLotto(): Lotto {
            return Lotto(1, 2, 3, 4, 5, 6)
        }
    }
}
