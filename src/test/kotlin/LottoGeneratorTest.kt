import domain.LottoGenerator
import domain.model.PurchaseMoney
import domain.model.lotto.LottoNumber
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
    fun `구입 금액이 1000원 단위가 아닌 경우 예외가 발생한다`() {
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
    fun `구입 금액에 해당하는 수의 로또를 발행한다`() {
        assertThat(
            lottoGenerator.generateLottos(
                PurchaseMoney(5000)
            ).size
        ).isEqualTo(5)
    }

    @Test
    fun `로또를 발행한다`() {
        // given
        val lottoGenerator = LottoGenerator {
            listOf(1, 2, 3, 4, 5, 6)
        }
        // when
        val lotto = lottoGenerator.generateLottos(PurchaseMoney(1000)).first()
        // then
        assertThat(lotto.numbers).isEqualTo(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            )
        )
    }
}
