import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoGeneratorTest {

    private lateinit var lottoGenerator: LottoGenerator

    @BeforeEach
    fun setUpLottoGenerator() {
        lottoGenerator = LottoGenerator()
    }

    @Test
    fun `구입 금액이 1000원 단위가 아닌 경우`() {
        val purchaseMoney = PurchaseMoney(3500)
        assertThrows<IllegalArgumentException> {
            lottoGenerator.generateLottos(purchaseMoney)
        }
    }

    @Test
    fun `구입 금액이 1000원 단위인 경우`() {
        val purchaseMoney = PurchaseMoney(3000)
        assertDoesNotThrow {
            lottoGenerator.generateLottos(purchaseMoney)
        }
    }

    @Test
    fun `구입 금액만큼 로또를 발행한다`() {
        assertThat(
            lottoGenerator.generateLottos(
                PurchaseMoney(5000)
            ).size
        ).isEqualTo(5)
    }
}
